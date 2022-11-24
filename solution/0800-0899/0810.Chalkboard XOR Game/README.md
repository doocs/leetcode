# [810. 黑板异或游戏](https://leetcode.cn/problems/chalkboard-xor-game)

[English Version](/solution/0800-0899/0810.Chalkboard%20XOR%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>黑板上写着一个非负整数数组 <code>nums[i]</code> 。</p>

<p>Alice 和 Bob 轮流从黑板上擦掉一个数字，Alice 先手。如果擦除一个数字后，剩余的所有数字按位异或运算得出的结果等于 <code>0</code> 的话，当前玩家游戏失败。&nbsp;另外，如果只剩一个数字，按位异或运算得到它本身；如果无数字剩余，按位异或运算结果为&nbsp;<code>0</code>。</p>

<p>并且，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 <code>0</code> ，这个玩家获胜。</p>

<p>假设两个玩家每步都使用最优解，当且仅当 Alice 获胜时返回 <code>true</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> nums = [1,1,2]
<strong>输出:</strong> false
<strong>解释:</strong> 
Alice 有两个选择: 擦掉数字 1 或 2。
如果擦掉 1, 数组变成 [1, 2]。剩余数字按位异或得到 1 XOR 2 = 3。那么 Bob 可以擦掉任意数字，因为 Alice 会成为擦掉最后一个数字的人，她总是会输。
如果 Alice 擦掉 2，那么数组变成[1, 1]。剩余数字按位异或得到 1 XOR 1 = 0。Alice 仍然会输掉游戏。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1]
<strong>输出:</strong> true
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3]
<strong>输出:</strong> true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>16</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

根据游戏规则，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果为 $0$，这个玩家获胜。由于 Alice 先手，因此当 `nums` 中所有数字的异或结果为 $0$ 时，Alice 可以获胜。

当 `nums` 中所有数字的异或结果不为 $0$ 时，我们不妨考虑从数组 `nums` 的长度奇偶性来分析 Alice 的获胜情况。

当 `nums` 的长度为偶数时，如果 Alice 必败，那么只有一种情况，就是 Alice 无论擦掉哪个数字，剩余所有数字的异或结果都等于 $0$。我们来分析一下是否存在这种情况。

假设数组 `nums` 长度为 $n$，并且 $n$ 是偶数，记所有数字异或结尾为 $S$，则有：

$$
S = nums[0] \oplus nums[1] \oplus \cdots \oplus nums[n-1] \neq 0
$$

我们记 $S_i$ 为数组 `nums` 擦掉第 $i$ 个数字后的异或结果，那么有：

$$
S_i \oplus nums[i] = S
$$

等式两边同时异或 $nums[i]$，得到：

$$
S_i = S \oplus nums[i]
$$

如果无论 Alice 擦掉哪个数字，剩余所有数字的异或结果都等于 $0$，那么对所有 $i$，都有 $S_i = 0$，即：

$$
S_0 \oplus S_1 \oplus \cdots \oplus S_{n-1} = 0
$$

我们将 $S_i = S \oplus nums[i]$ 代入上式，得到：

$$
S \oplus nums[0] \oplus S \oplus nums[1] \oplus \cdots \oplus S \oplus nums[n-1] = 0
$$

上式共有 $n$（偶数）个 $S$，而 $nums[0] \oplus nums[1] \oplus \cdots \oplus nums[n-1]$ 也等于 $S$，因此上式等价于 $0 \oplus S = 0$。这与 $S \neq 0$ 矛盾，因此不存在这种情况。因此当 `nums` 的长度为偶数时，Alice 必胜。

如果长度为奇数，那么 Alice 擦掉一个数字后，剩余数字个数为偶数，也就是将偶数长度的情况留给 Bob，那么 Bob 必胜，也即 Alice 必败。

综上，当 `nums` 的长度为偶数，或者 `nums` 中所有数字的异或结果为 $0$ 时，Alice 可以获胜。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def xorGame(self, nums: List[int]) -> bool:
        return len(nums) % 2 == 0 or reduce(xor, nums) == 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean xorGame(int[] nums) {
        return nums.length % 2 == 0 || Arrays.stream(nums).reduce(0, (a, b) -> a ^ b) == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool xorGame(vector<int>& nums) {
        if (nums.size() % 2 == 0) return true;
        int x = 0;
        for (int& v : nums) x ^= v;
        return x == 0;
    }
};
```

### **Go**

```go
func xorGame(nums []int) bool {
	if len(nums)%2 == 0 {
		return true
	}
	x := 0
	for _, v := range nums {
		x ^= v
	}
	return x == 0
}
```

### **...**

```

```

<!-- tabs:end -->
