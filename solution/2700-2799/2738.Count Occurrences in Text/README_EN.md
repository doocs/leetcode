# [2738. Count Occurrences in Text 🔒](https://leetcode.com/problems/count-occurrences-in-text)

[中文文档](/solution/2700-2799/2738.Count%20Occurrences%20in%20Text/README.md)

<!-- tags:Database -->

## Description

<p>Table:<font face="monospace"> <code>Files</code></font></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-- ----------+---------+
| file_name   | varchar |
| content     | text    |
+-------------+---------+
file_name is the column with unique values of this table. 
Each row contains file_name and the content of that file.
</pre>

<p>Write a solution to find&nbsp;the number of files that have at least one occurrence of the words&nbsp;<strong>&#39;bull&#39;</strong> and <strong>&#39;bear&#39;</strong> as a <strong>standalone word</strong>, respectively, disregarding any instances where it appears without space on either side (e.g. &#39;bullet&#39;,&nbsp;&#39;bears&#39;, &#39;bull.&#39;,&nbsp;or &#39;bear&#39;&nbsp;at the beginning or end of a sentence will <strong>not</strong> be considered)&nbsp;</p>

<p>Return <em>the word &#39;bull&#39; and &#39;bear&#39; along with the corresponding number of occurrences in <strong>any order.</strong></em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong>&nbsp;
Files table:
+------------+----------------------------------------------------------------------------------+
| file_name  | content                                                                         | 
+------------+----------------------------------------------------------------------------------+
| draft1.txt | The stock exchange predicts a bull market which would make many investors happy. | 
| draft2.txt | The stock exchange predicts a bull market which would make many investors happy, |
|&nbsp;           | but analysts warn of possibility of too much optimism and that in fact we are    |
|&nbsp;           | awaiting a bear market.                                                          | 
| draft3.txt | The stock exchange predicts a bull market which would make many investors happy, |
|&nbsp;           | but analysts warn of possibility of too much optimism and that in fact we are    |
|&nbsp;           | awaiting a bear market. As always predicting the future market is an uncertain   |
|            | game and all investors should follow their instincts and best practices.         | 
+------------+----------------------------------------------------------------------------------+
<strong>Output:</strong>&nbsp;
+------+-------+
| word | count | &nbsp;
+------+-------+
| bull |&nbsp;3     |&nbsp;
| bear |&nbsp;2     | 
+------+-------+
<strong>Explanation:</strong>&nbsp;
- The word &quot;bull&quot; appears 1 time in &quot;draft1.txt&quot;, 1 time in &quot;draft2.txt&quot;, and 1 time in &quot;draft3.txt&quot;. Therefore, the total number of occurrences for the word &quot;bull&quot; is 3.
- The word &quot;bear&quot; appears 1 time in &quot;draft2.txt&quot;, and 1 time in &quot;draft3.txt&quot;. Therefore, the total number of occurrences for the word &quot;bear&quot; is 2.

</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT 'bull' AS word, COUNT(*) AS count
FROM Files
WHERE content LIKE '% bull %'
UNION
SELECT 'bear' AS word, COUNT(*) AS count
FROM Files
WHERE content LIKE '% bear %';
```

<!-- tabs:end -->

<!-- end -->
