# [721. Accounts Merge](https://leetcode.com/problems/accounts-merge)

[中文文档](/solution/0700-0799/0721.Accounts%20Merge/README.md)

## Description
<p>Given a list <code>accounts</code>, each element <code>accounts[i]</code> is a list of strings, where the first element <code>accounts[i][0]</code> is a <i>name</i>, and the rest of the elements are <i>emails</i> representing emails of the account.</p>



<p>Now, we would like to merge these accounts.  Two accounts definitely belong to the same person if there is some email that is common to both accounts.  Note that even if two accounts have the same name, they may belong to different people as people could have the same name.  A person can have any number of accounts initially, but all of their accounts definitely have the same name.</p>



<p>After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails <b>in sorted order</b>.  The accounts themselves can be returned in any order.</p>



<p><b>Example 1:</b><br />

<pre style="white-space: pre-wrap">

<b>Input:</b> 

accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]

<b>Output:</b> [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]

<b>Explanation:</b> 

The first and third John's are the same person as they have the common email "johnsmith@mail.com".

The second John and Mary are different people as none of their email addresses are used by other accounts.

We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 

['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

</pre>

</p>



<p><b>Note:</b>

<li>The length of <code>accounts</code> will be in the range <code>[1, 1000]</code>.</li>

<li>The length of <code>accounts[i]</code> will be in the range <code>[1, 10]</code>.</li>

<li>The length of <code>accounts[i][j]</code> will be in the range <code>[1, 30]</code>.</li>

</p>


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