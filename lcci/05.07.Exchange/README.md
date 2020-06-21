# [面试题 05.07. 配对交换](https://leetcode-cn.com/problems/exchange-lcci)

## 题目描述
<!-- 这里写题目描述 -->
<p>配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：num = 2（或者0b10）
<strong> 输出</strong> 1 (或者 0b01)
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>：num = 3
<strong> 输出</strong>：3
</pre>

<p> <strong>提示:</strong></p>

<ol>
<li><code>num</code>的范围在[0, 2^30 - 1]之间，不会发生整数溢出。</li>
</ol>


## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int exchangeBits(int num) {
        int t1 = num >> 1;
    	int t2 = num << 1;
    	return t1 & 0x55555555 | t2 & 0xaaaaaaaa;
    }
}
```

### ...
```

```
