# [292. Nim 游戏](https://leetcode.cn/problems/nim-game)

[English Version](/solution/0200-0299/0292.Nim%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你和你的朋友，两个人一起玩&nbsp;<a href="https://baike.baidu.com/item/Nim游戏/6737105" target="_blank">Nim 游戏</a>：</p>

<ul>
	<li>桌子上有一堆石头。</li>
	<li>你们轮流进行自己的回合，&nbsp;<strong>你作为先手&nbsp;</strong>。</li>
	<li>每一回合，轮到的人拿掉&nbsp;1 - 3 块石头。</li>
	<li>拿掉最后一块石头的人就是获胜者。</li>
</ul>

<p>假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 <code>n</code> 的情况下赢得游戏。如果可以赢，返回 <code>true</code>；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong><code>n = 4</code>
<strong>输出：</strong>false 
<strong>解释：</strong>以下是可能的结果:
1. 移除1颗石头。你的朋友移走了3块石头，包括最后一块。你的朋友赢了。
2. 移除2个石子。你的朋友移走2块石头，包括最后一块。你的朋友赢了。
3.你移走3颗石子。你的朋友移走了最后一块石头。你的朋友赢了。
在所有结果中，你的朋友是赢家。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学推理**

第一个得到 $4$ 的倍数（即 $n$ 能被 $4$ 整除）的将会输掉比赛。

证明：

1. 当 $n=4$，无论第一个玩家选择 $1/2/3$ 哪个数字，第二个玩家总能选择剩下的数字，**第一个玩家将会输掉比赛**。
1. 当 $4<n<8$，即 ($n=5,6,7$)，第一个玩家可以相应地将数字减少为 $4$，那么 $4$ 这个死亡数字给到了第二个玩家，第二个玩家将会输掉比赛。
1. 当 $n=8$，无论第一个玩家选择 $1/2/3$ 哪个数字，都会把 $4<n<8$ 的数字留给第二个，**第一个玩家将会输掉比赛**。
1. ...
1. 依次类推，当玩家拿到 $n$ 这个数字，且 $n$ 能被 $4$ 整除，他将会输掉比赛，否则他将赢得比赛。

时间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canWinNim(self, n: int) -> bool:
        return n % 4 != 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
```

### **TypeScript**

```ts
function canWinNim(n: number): boolean {
    return n % 4 != 0;
}
```

### **C++**

```cpp
class Solution {
public:
    bool canWinNim(int n) {
        return n % 4 != 0;
    }
};
```

### **Go**

```go
func canWinNim(n int) bool {
	return n%4 != 0
}
```

### **Rust**

```rust
impl Solution {
    pub fn can_win_nim(n: i32) -> bool {
        n % 4 != 0
    }
}
```

### **...**

```

```

<!-- tabs:end -->
