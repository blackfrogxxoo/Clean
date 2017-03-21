package me.shaoown.clean;

import org.junit.Test;

import me.shaoown.clean.demo.MockClient;
import me.shaoown.clean.demo.MockRequest;
import me.shaoown.clean.demo.MockUi;

/**
 * Created by black on 2017/3/21.
 */

public class IClientTest {
    @Test
    public void testRegister() {
        TestDemo.testAction0Request();
        TestDemo.testAction1Request();
    }

    @Test
    public void testMock() {
        MockClient client = new MockClient();
        client.register();
        MockUi ui = new MockUi();
        ui.request(new MockRequest());
        client.unregister();
        ui.request(new MockRequest());
    }
}
