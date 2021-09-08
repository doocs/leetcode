# [LCP 01. 猜数字](https://leetcode-cn.com/problems/guess-numbers)

## 题目描述

<!-- 这里写题目描述 -->

<p>小A 和 小B 在玩猜数字。小B 每次从 1, 2, 3 中随机选择一个，小A 每次也从 1, 2, 3 中选择一个猜。他们一共进行三次这个游戏，请返回 小A 猜对了几次？</p>

<p>输入的<code>guess</code>数组为 小A 每次的猜测，<code>answer</code>数组为 小B 每次的选择。<code>guess</code>和<code>answer</code>的长度都等于3。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>guess = [1,2,3], answer = [1,2,3]
<strong>输出：</strong>3
<strong>解释：</strong>小A 每次都猜对了。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>guess = [2,2,3], answer = [3,2,1]
<strong>输出：</strong>1
<strong>解释：</strong>小A 只猜对了第二次。</pre>

<p> </p>

<p><strong>限制：</strong></p>

<ol>
	<li><code>guess</code> 的长度 = 3</li>
	<li><code>answer</code> 的长度 = 3</li>
	<li><code>guess</code> 的元素取值为 <code>{1, 2, 3}</code> 之一。</li>
	<li><code>answer</code> 的元素取值为 <code>{1, 2, 3}</code> 之一。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def game(self, guess: List[int], answer: List[int]) -> int:
        return sum(1 for i in range(3) if guess[i] == answer[i])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int game(int[] guess, int[] answer) {
        int times = 0;
        for (int i = 0; i < 3; ++i) {
            times += (guess[i] == answer[i] ? 1 : 0);
        }
        return times;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int game(vector<int>& guess, vector<int>& answer) {
        int times = 0;
        for (int i = 0; i < 3; ++i) times += guess[i] == answer[i];
        return times;
    }
};
```

### **Go**

```go
func game(guess []int, answer []int) int {
	times := 0
	for i := 0; i < 3; i++ {
		if guess[i] == answer[i] {
			times++
		}
	}
	return times
}
```

### **...**

```

```

<!-- tabs:end -->
