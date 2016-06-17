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
		System.out.println("Master started");
		
		ChunkServer server;
		server = new ChunkServer("127.0.0.1");
		LocateRegistry.createRegistry(9600);
		Naming.rebind("rmi://127.0.0.1:9600/chunkserver", server);
		System.out.println("Datanode started");
		
//		ChunkServer server2;
//		server2 = new ChunkServer("127.0.0.1", "/data/chunkserver2/");
//		LocateRegistry.createRegistry(9601);
//		Naming.rebind("rmi://127.0.0.1:9601/chunkserver", server);
//		System.out.println("Datanode2 started");
		
        DFS dfs;
        dfs = new DFS("127.0.0.1:9500");
        
        String fileName = "ipp.pcap";
        
        dfs.upload(fileName);
        System.out.println(master.getChunks(fileName));
        
        System.out.println(dfs.getFileList());
	}
}
