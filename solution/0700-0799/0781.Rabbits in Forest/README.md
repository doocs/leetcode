# [781. 森林中的兔子](https://leetcode.cn/problems/rabbits-in-forest)

[English Version](/solution/0700-0799/0781.Rabbits%20in%20Forest/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>森林中有未知数量的兔子。提问其中若干只兔子<strong> "还有多少只兔子与你（指被提问的兔子）颜色相同?"</strong> ，将答案收集到一个整数数组 <code>answers</code> 中，其中 <code>answers[i]</code> 是第 <code>i</code> 只兔子的回答。</p>

<p>给你数组 <code>answers</code> ，返回森林中兔子的最少数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>answers = [1,1,2]
<strong>输出：</strong>5
<strong>解释：</strong>
两只回答了 "1" 的兔子可能有相同的颜色，设为红色。 
之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
设回答了 "2" 的兔子为蓝色。 
此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。 
因此森林中兔子的最少数量是 5 只：3 只回答的和 2 只没有回答的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>answers = [10,10,10]
<strong>输出：</strong>11
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= answers.length &lt;= 1000</code></li>
	<li><code>0 &lt;= answers[i] &lt; 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

两只相同颜色的兔子看到的其他同色兔子数一定相同，若两只兔子看到的其它同色兔子数不同，那么这两只兔子颜色也不同。

因此，将 `answers` 中值相同的元素分为一组，对于每一组，计算出兔子的最少数量，然后将所有组的计算结果累加，就是最终的答案。

如果有 x 只兔子都回答 y，则至少有 `⌈x / (y + 1)⌉` 种不同的颜色，且每种颜色有 `y + 1` 只兔子。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numRabbits(self, answers: List[int]) -> int:
        counter = Counter(answers)
        return sum([math.ceil(v / (k + 1)) * (k + 1) for k, v in counter.items()])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int e : answers) {
            counter.put(e, counter.getOrDefault(e, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int answer = entry.getKey(), count = entry.getValue();
            res += (int) Math.ceil(count / ((answer + 1) * 1.0)) * (answer + 1);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
