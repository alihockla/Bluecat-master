# Bluecat-master
CSC 376 Distributed Systems Program Assignment 1

All Bluecat tests pass (tests located in shell scripts) when run with corresponding Echo Client/Server programs as well as Bluecat Client/Server programs with a small modification when testing ./Server_logging.sh and ./Server_file_line.sh.

These two tests only work with a modified client socket which can be found in ConfiguredClientSocket.java. These two tests work when run with ConfiguredClientTest.java but if run with Echo Client or Bluecat Client the data is sent line by line but program does not shutdown on its own :(
