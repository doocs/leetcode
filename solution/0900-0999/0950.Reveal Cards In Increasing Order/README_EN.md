# [950. Reveal Cards In Increasing Order](https://leetcode.com/problems/reveal-cards-in-increasing-order)

[中文文档](/solution/0900-0999/0950.Reveal%20Cards%20In%20Increasing%20Order/README.md)

## Description

<p>You are given an integer array <code>deck</code>. There is a deck of cards where every card has a unique integer. The integer on the <code>i<sup>th</sup></code> card is <code>deck[i]</code>.</p>

<p>You can order the deck in any order you want. Initially, all the cards start face down (unrevealed) in one deck.</p>

<p>You will do the following steps repeatedly until all cards are revealed:</p>

<ol>
	<li>Take the top card of the deck, reveal it, and take it out of the deck.</li>
	<li>If there are still cards in the deck then put the next top card of the deck at the bottom of the deck.</li>
	<li>If there are still unrevealed cards, go back to step 1. Otherwise, stop.</li>
</ol>

<p>Return <em>an ordering of the deck that would reveal the cards in increasing order</em>.</p>

<p><strong>Note</strong> that the first entry in the answer is considered to be the top of the deck.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> deck = [17,13,11,2,3,5,7]
<strong>Output:</strong> [2,13,3,11,5,17,7]
<strong>Explanation:</strong> 
We get the deck in the order [17,13,11,2,3,5,7] (this order does not matter), and reorder it.
After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
We reveal 13, and move 17 to the bottom.  The deck is now [17].
We reveal 17.
Since all the cards revealed are in increasing order, the answer is correct.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> deck = [1,1000]
<strong>Output:</strong> [1,1000]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= deck.length &lt;= 1000</code></li>
	<li><code>1 &lt;= deck[i] &lt;= 10<sup>6</sup></code></li>
	<li>All the values of <code>deck</code> are <strong>unique</strong>.</li>
</ul>

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
