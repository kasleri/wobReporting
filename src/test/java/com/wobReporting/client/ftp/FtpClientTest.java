package com.wobReporting.client.ftp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.DirectoryEntry;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class FtpClientTest {

    @Autowired
    private FtpClient ftpClient;
    @Value("${report.json.file-name}")
    private String jsonFile;
    private FakeFtpServer fakeFtpServer;

    @BeforeEach
    void setUp() throws IOException {
        fakeFtpServer = new FakeFtpServer();
        fakeFtpServer.addUserAccount(new UserAccount("user", "password", "/data"));

        FileSystem fileSystem = new UnixFakeFileSystem();
        fileSystem.add(new DirectoryEntry("/data"));
        fileSystem.add(new FileEntry("/data/test.txt", "This is a test content!"));
        fakeFtpServer.setFileSystem(fileSystem);
        fakeFtpServer.setServerControlPort(0);

        fakeFtpServer.start();

        ftpClient = new FtpClient("localhost", fakeFtpServer.getServerControlPort(), "user", "password");
        /*ftpClient = new FtpClient(PropertiesLoader.getProperty("ftp.client.server"),
                Integer.parseInt(PropertiesLoader.getProperty("ftp.client.port")),
                PropertiesLoader.getProperty("ftp.client.user"),
                PropertiesLoader.getProperty("ftp.client.password"));*/

        ftpClient.open();
    }

    @AfterEach
    void tearDown() throws IOException {
        ftpClient.close();
        fakeFtpServer.stop();
    }

    @Test
    public void listFileTest() throws IOException {
        Collection<String> files = ftpClient.listFiles("");

        assertThat(files).contains("test.txt");
    }

    @Test
    public void givenRemoteFile_whenDownloading_thenItIsOnTheLocalFilesystem() throws IOException {
        ftpClient.downloadFile("/test.txt", "downloaded_test.txt");

        assertThat(new File("downloaded_test.txt")).exists();
        new File("downloaded_test.txt").delete();
    }

    @Test
    public void uploadFileTest() throws IOException {

        File file = new File(jsonFile);

        ftpClient.putFileToPath(file, "report.json");

        ftpClient.downloadFile("/report.json", "downloaded_report.json");
        assertThat(new File("downloaded_report.json")).exists();
        new File("downloaded_report.json").delete();
    }
}