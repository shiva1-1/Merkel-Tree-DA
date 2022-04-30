# Merkel-Tree-DA
## Introduction
Merkle tree is named after Ralph Merkle, it is a tree data structure where non-leaf nodes are a hash of its child nodes and leaf nodes are a hash of a block of data. It has a branching factor of 2 (it can have a maximum of 2 children). 
-	Merkel trees allow efficient and secure verification of the contents of a large data structure.
-	Merkle Tree is also known as Hash Tree.

## Architecture
<img src="https://ik.imagekit.io/cl22iiharm/image_cOTfhd3xb.png?ik-sdk-version=javascript-1.4.3&updatedAt=1651082560519" >

## Algorithm
Basically, Merkle trees are data structure trees where the non-leaf node is defined as a hash value of its respective child nodes. This also means that the Merkle tree is inverted down where the leaf nodes are the lowest node. 

At the core of Merkle trees, we need to learn three important terms. They are as below:
* Merkle Root
* Leaf Nodes
* Non-Leaf Nodes

To make Merkle trees work, hashing is used. It simply does the hashing pairs of nodes repeatedly until only one hash value is left. The left hash value is known as Merkle Root or the Root Hash. The tree is created from the bottom up using the individual transactions hashes. The individual transaction hashes are also known as Transaction IDs. 

The leaf nodes are the nodes that contain transactional data hashes. In the case of the non-leaf nodes, they store the hash of the two previous hashes.

## Example using illustration
![image](https://user-images.githubusercontent.com/104256185/166100174-ad2bdb0d-7af4-4e13-990e-cb9fa809bbed.png)


Here, we see that four transactions have taken place in the block. These transactions are named X, Y, Z, and W. The transactions are then hashed and then stored in leaf nodes which we name as Hash X, Hash Y, Hash Z, and Hash W.

Once done, the leaf nodes of Hash X, Y, Z, and W are again hashed and created into a combined hash of XY and ZW. Finally, these two hashes are used to create the Merkle Root or Root Hash.

The whole process of hashing can be done on a very large data set which makes the Merkle Trees data structure useful in the case of decentralized networks.

As we discussed earlier, hashing algorithms usage depends on the implementation. However, one of the most common hash functions that are used includes the SHA-2 cryptographic hash function. 

So, a transaction can be verified if the previous transactions are verifiable, thanks to the hash values.
