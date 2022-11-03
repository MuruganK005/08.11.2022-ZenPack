package com.ZenPack.utils;

import org.apache.zookeeper.KeeperException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ZookeeperConnectionTest {
    @Test
    @Disabled("TODO: Complete this test")
    void testConnect() throws IOException, InterruptedException {
        (new ZookeeperConnection()).connect("localhost");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testConnect2() throws IOException, InterruptedException {

        (new ZookeeperConnection()).connect("/");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testConnect3() throws IOException, InterruptedException {
        (new ZookeeperConnection()).connect("/common");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testConnect4() throws IOException, InterruptedException {
        (new ZookeeperConnection()).connect("/opt/config/");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testConnect5() throws IOException, InterruptedException {
        (new ZookeeperConnection()).connect("//common");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testConnect6() throws IOException, InterruptedException {
        (new ZookeeperConnection()).connect("/java.lang.String");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testConnect7() throws IOException, InterruptedException {
        (new ZookeeperConnection()).connect("/common/common");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testClose() throws InterruptedException {
        (new ZookeeperConnection()).close();
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGetZKData() throws IOException, InterruptedException, KeeperException {
        (new ZookeeperConnection()).getZKData();
    }

    @Test
    void testCopyQueryFiles() {
        (new ZookeeperConnection()).copyQueryFiles();
    }
}

