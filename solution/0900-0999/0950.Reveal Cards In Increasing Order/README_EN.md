# [950. Reveal Cards In Increasing Order](https://leetcode.com/problems/reveal-cards-in-increasing-order)

[中文文档](/solution/0900-0999/0950.Reveal%20Cards%20In%20Increasing%20Order/README.md)

## Description

<p>In a deck of cards, every card has a unique integer.&nbsp; You can order the deck in&nbsp;any order you want.</p>

<p>Initially, all the cards start face down (unrevealed) in one deck.</p>

<p>Now, you do the following steps repeatedly, until all cards are revealed:</p>

<ol>
	<li>Take the top card of the deck, reveal it, and take it out of the deck.</li>
	<li>If there are still cards in the deck, put the next top card of the deck at&nbsp;the bottom of the deck.</li>
	<li>If there are still unrevealed cards, go back to step 1.&nbsp; Otherwise, stop.</li>
</ol>

<p>Return an ordering of the deck that would reveal the cards&nbsp;in <strong>increasing order.</strong></p>

<p>The first entry in the answer is considered to be the top of the deck.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[17,13,11,2,3,5,7]</span>

<strong>Output: </strong><span id="example-output-1">[2,13,3,11,5,17,7]</span>

<strong>Explanation: </strong>

We get the deck in the order [17,13,11,2,3,5,7] (this order doesn't matter), and reorder it.

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

<div>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 1000</code></li>
	<li><code>1 &lt;= A[i] &lt;= 10^6</code></li>
	<li><code>A[i] != A[j]</code>&nbsp;for all&nbsp;<code>i != j</code></li>
</ol>

</div>

</div>

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
