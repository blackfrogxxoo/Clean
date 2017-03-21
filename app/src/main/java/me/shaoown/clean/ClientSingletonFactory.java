package me.shaoown.clean;

/**
 * Created by black on 2017/3/21.
 */

public enum ClientSingletonFactory {
    INSTANCE;
    private StringListClient stringListClient = new StringListClient();
    public StringListClient getStringListClient() {
        return stringListClient;
    }
}
