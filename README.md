# Apolu
Library with more than 21 information structures. This was first created for having an own structure library. More documentation is comming.

## Structures:
### Package lista:
 - _ListaSimple:_ Linked list that can only be sequentialy browsed in one way, in the order they were added. It's very tiny for the memory.
 - _ListaDoble:_ Linked list that can be sequentialy browsed in two ways. You can access with the node to the left node and to the right node anytime.
 - _Conjunto:_ Linked list that extends the ListaSimple structure. Its main objective is to have just an occurrence per item. It doesn't allow repeated elements.
 
### Package arboles:
 - _ArbolBinario:_ The most primitive tree. Its nodes have two children, the left one and the right one. It's very useful for organized items.
 - _ArbolGeneral:_ An adaptative tree. Its nodes have as many children as the programmer wants. It's very useful for building paths or graphs.
 - _ArbolAVL:_ A balanced tree structure. It finds the way to add nodes mantaining all the nodes easy to find.
 
### Package pilasYColas:
 - _Pila:_ Also known as stack. It's an extension of ListaSimple. It has a LIFO (Last in, first out) main rule. If you want to know more about stacks: http://lmgtfy.com/?q=Stack
 - _Cola:_ Also known as qeue. It implements Qeue interface. It has a FIFO (First in, first out) main rule.
 
