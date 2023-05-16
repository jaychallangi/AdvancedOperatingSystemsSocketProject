# AdvancedOperatingSystemsSocketProject

Main.java has the code to create a randomly generated F1 and F2 in P1 and P2 respectively.
If it is wished to use your own F1 and F2 move them into P1 and P2 folders respectively and do
NOT run java Main
Due to the specifications of this program, the stop conditions for listening for messages is 
a for loop as we know specifically how many message will be sent between client and server.
If this was unknown I would have the sender send a close (end) message to indicate the end of 
transmission. I have the infastructure set up but it is not used do to for loop structure signalling 
the end of transmission.

Steps To Run:
1) cd into the D1 folder
2) run make (Makefile)
3) run java Main (Optional, explained later)
4) In order:
    1) run java P3/Server to start the server (Creates F3 at intialization)
    2) run java P1/Client1 to start client 1 (Opens F1 at initialization)
    3) run java P2/Client2 to start client 2 (Opens F2 at initialization)
5) After this there should be F3 file in all P folders
