# [312. Burst Balloons](https://leetcode.com/problems/burst-balloons)

[中文文档](/solution/0300-0399/0312.Burst%20Balloons/README.md)

## Description
<p>Given <code>n</code> balloons, indexed from <code>0</code> to <code>n-1</code>. Each balloon is painted with a number on it represented by array <code>nums</code>. You are asked to burst all the balloons. If the you burst balloon <code>i</code> you will get <code>nums[left] * nums[i] * nums[right]</code> coins. Here <code>left</code> and <code>right</code> are adjacent indices of <code>i</code>. After the burst, the <code>left</code> and <code>right</code> then becomes adjacent.</p>



<p>Find the maximum coins you can collect by bursting the balloons wisely.</p>



<p><b>Note:</b></p>



<ul>

	<li>You may imagine <code>nums[-1] = nums[n] = 1</code>. They are not real therefore you can not burst them.</li>

	<li>0 &le; <code>n</code> &le; 500, 0 &le; <code>nums[i]</code> &le; 100</li>

</ul>



<p><b>Example:</b></p>



<pre>

<b>Input:</b> <code>[3,1,5,8]</code>

<b>Output:</b> <code>167 

<strong>Explanation: </strong></code>nums = [3,1,5,8] --&gt; [3,5,8] --&gt;   [3,8]   --&gt;  [8]  --&gt; []

&nbsp;            coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

</pre>


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