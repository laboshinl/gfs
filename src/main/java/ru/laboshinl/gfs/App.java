package ru.laboshinl.gfs;

import java.io.InputStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import ru.laboshinl.gfs.server.chunkserver.ChunkServer;
import ru.laboshinl.gfs.server.master.Master;

public class App {

	public static void main(String[] args) throws Exception {

		Master master = new Master();
		LocateRegistry.createRegistry(9500);
		Naming.rebind("rmi://127.0.0.1:9500/master", master);

		ChunkServer server;
		server = new ChunkServer("127.0.0.1");
		LocateRegistry.createRegistry(9600);
		Naming.rebind("rmi://127.0.0.1:9600/chunkserver", server);
		
        DFS dfs;
        dfs = new DFS("127.0.0.1:9500");
        
        String fileName = "test.pcap";
        
        dfs.upload(fileName);
	}
}
