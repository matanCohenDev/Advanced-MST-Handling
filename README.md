# Advanced MST Handling

This repository contains an implementation of Prim's algorithm for finding a Minimum Spanning Tree (MST) and a custom algorithm to handle the addition of a new edge to the MST.

## Overview

The project addresses the following challenges:
1. Finding a Minimum Spanning Tree (MST) using Prim's algorithm.
2. Adding a new edge to the MST, which creates a cycle by definition, and removing the heaviest edge from this cycle to maintain the MST properties.

The custom algorithm identifies the cycle created by the new edge and efficiently removes the maximum weight edge, ensuring the graph remains a valid MST.

## Features

- **Prim's Algorithm**: Builds the initial MST.
- **Cycle Detection**: Efficiently identifies the cycle formed by the addition of a new edge.
- **Edge Replacement**: Removes the heaviest edge in the cycle to maintain the MST.

## How the Algorithm Works

1. **Build the MST**: Using Prim's algorithm, the MST is constructed starting from a given vertex.
2. **Add Edge to MST**: When a new edge is added, it may form a cycle. The algorithm detects this cycle by traversing the parent relationships of the vertices involved.
3. **Cycle Handling**: The heaviest edge in the cycle is identified and removed. If the new edge is the heaviest, it is discarded, maintaining the original MST.

## Code Highlights

### Prim's Algorithm

The `prim()` method builds an MST by greedily selecting the smallest edge and ensuring no cycles are formed.

### Adding a New Edge

The `AddEdgeToMst()` method:
- Checks if the new edge affects the MST.
- Identifies the cycle created by the new edge.
- Removes the heaviest edge in the cycle.

## Usage

To run the algorithm:
1. Create a graph representation with vertices and edges.
2. Use the `prim()` method to generate the MST.
3. Use the `AddEdgeToMst()` method to add edges and maintain the MST properties.

## Example

```java
Graph graph = new Graph();
// Add vertices and edges to the graph
PrimAlgorithm primAlgorithm = new PrimAlgorithm();
Graph mst = primAlgorithm.prim(graph, startVertex);

Edge newEdge = new Edge(sourceVertex, destVertex, weight);
boolean result = primAlgorithm.AddEdgeToMst(mst, newEdge);

if (result) {
    System.out.println("MST updated successfully!");
} else {
    System.out.println("New edge is the heaviest in the cycle and was not added.");
}
