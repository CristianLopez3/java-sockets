<h1 align="center" id="start">Socket Programming 🧦</h1>

### Table of Contents

- [Introduction](#start)
- [Dockerfile](#dockerfile)
- [TCP (Transmission Control Protocol)](#tcp)

A socket in **computer networking** is a communication endpoint that allows **bidirectional data** flow 
between two nodes on a network. It's identified by a combination of an **IP address** and a **port** 
number, facilitating the **transmission of data** between applications running on different computers. 
Think of it as a virtual interface 🌐 that programs use to **establish connections** and **exchange 
information** 📡. It enables reliable, **stream-oriented**, and connection-oriented communication 🔄 between 
client and server processes, akin to a designated channel 📶 for **data transmission** across the **network 
infrastructure**.

<h2 id="tcp">TCP (Transmission Control Protocol) 📦</h2>


TCP (Transmission Control Protocol) is a fundamental protocol in computer networking that ensures 
reliable and ordered delivery of data between devices over a network 🌐. It breaks down large 
chunks of data into smaller packets 📦, sends them across the network, and then reassembles them 
in the correct order at the receiving end. TCP handles error checking, resending lost packets, and 
flow control to prevent overwhelming the receiving device. Think of TCP as a careful mail 
carrier 📬 who meticulously organizes your letters 💌, ensures they all arrive safely, and asks 
for a signature upon delivery to guarantee nothing gets lost or mixed up in transit. Its importance 
lies in providing a dependable communication channel for applications like web browsing, email, and 
file transfer, where data integrity and order are crucial.

<h3 id="dockerfile">Dockerfile 🐋  </h3>


Run the project with docker with the next commands:

```bash
  docker build -t socket-app .
  docker run -d -p 8080:8080 socket-app
```