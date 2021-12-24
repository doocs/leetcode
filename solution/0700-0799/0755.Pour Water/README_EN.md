# [755. Pour Water](https://leetcode.com/problems/pour-water)

[中文文档](/solution/0700-0799/0755.Pour%20Water/README.md)

## Description

<p>

We are given an elevation map, <code>heights[i]</code> representing the height of the terrain at that index. The width at each index is 1. After <code>V</code> units of water fall at index <code>K</code>, how much water is at each index?

</p><p>

Water first drops at index <code>K</code> and rests on top of the highest terrain or water at that index. Then, it flows according to the following rules:

<li>If the droplet would eventually fall by moving left, then move left.</li>

<li>Otherwise, if the droplet would eventually fall by moving right, then move right.</li>

<li>Otherwise, rise at it's current position.</li>

Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction.

Also, "level" means the height of the terrain plus any water in that column.

</p><p>

We can assume there's infinitely high terrain on the two sides out of bounds of the array. Also, there could not be partial water being spread out evenly on more than 1 grid block - each unit of water has to be in exactly one block.

<p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> heights = [2,1,1,2,1,2,2], V = 4, K = 3

<b>Output:</b> [2,2,2,3,2,2,2]

<b>Explanation:</b>

#       #

#       #

##  # ###

#########

 0123456    <- index



The first drop of water lands at index K = 3:



#       #

#   w   #

##  # ###

#########

 0123456    



When moving left or right, the water can only move to the same level or a lower level.

(By level, we mean the total height of the terrain plus any water in that column.)

Since moving left will eventually make it fall, it moves left.

(A droplet "made to fall" means go to a lower height than it was at previously.)



#       #

#       #

## w# ###

#########

 0123456    



Since moving left will not make it fall, it stays in place.  The next droplet falls:



#       #

#   w   #

## w# ###

#########

 0123456  



Since the new droplet moving left will eventually make it fall, it moves left.

Notice that the droplet still preferred to move left,

even though it could move right (and moving right makes it fall quicker.)



#       #

#  w    #

## w# ###

#########

 0123456  



#       #

#       #

##ww# ###

#########

 0123456  



After those steps, the third droplet falls.

Since moving left would not eventually make it fall, it tries to move right.

Since moving right would eventually make it fall, it moves right.



#       #

#   w   #

##ww# ###

#########

 0123456  



#       #

#       #

##ww#w###

#########

 0123456  



Finally, the fourth droplet falls.

Since moving left would not eventually make it fall, it tries to move right.

Since moving right would not eventually make it fall, it stays in place:



#       #

#   w   #

##ww#w###

#########

 0123456  



The final answer is [2,2,2,3,2,2,2]:



    #    

 ####### 

 ####### 

 0123456 

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> heights = [1,2,3,4], V = 2, K = 2

<b>Output:</b> [2,3,3,4]

<b>Explanation:</b>

The last droplet settles at index 1, since moving further left would not cause it to eventually fall to a lower height.

</pre>

</p>

<p><b>Example 3:</b><br />

<pre>

<b>Input:</b> heights = [3,1,3], V = 5, K = 1

<b>Output:</b> [4,4,4]

</pre>

</p>

<p><b>Note:</b><br><ol>

<li><code>heights</code> will have length in <code>[1, 100]</code> and contain integers in <code>[0, 99]</code>.</li>

<li><code>V</code> will be in range <code>[0, 2000]</code>.</li>

<li><code>K</code> will be in range <code>[0, heights.length - 1]</code>.</li>

</ol></p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
