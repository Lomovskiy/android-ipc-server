// IPCServer.aidl
package com.lomovskiy.ipcserver;

// Declare any non-default types here with import statements

interface IPCServer {

    int getPid();

    int getConnectionCount();

    void setDisplayedValue(String packageName, int pid, String data);

}
