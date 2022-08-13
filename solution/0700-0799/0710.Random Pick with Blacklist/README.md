# [710. 黑名单中的随机数](https://leetcode.cn/problems/random-pick-with-blacklist)

[English Version](/solution/0700-0799/0710.Random%20Pick%20with%20Blacklist/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数 <code>n</code> 和一个 <strong>无重复</strong> 黑名单整数数组&nbsp;<code>blacklist</code>&nbsp;。设计一种算法，从 <code>[0, n - 1]</code> 范围内的任意整数中选取一个&nbsp;<strong>未加入&nbsp;</strong>黑名单&nbsp;<code>blacklist</code>&nbsp;的整数。任何在上述范围内且不在黑名单&nbsp;<code>blacklist</code>&nbsp;中的整数都应该有 <strong>同等的可能性</strong> 被返回。</p>

<p>优化你的算法，使它最小化调用语言 <strong>内置</strong> 随机函数的次数。</p>

<p>实现&nbsp;<code>Solution</code>&nbsp;类:</p>

<ul>
	<li><code>Solution(int n, int[] blacklist)</code>&nbsp;初始化整数 <code>n</code> 和被加入黑名单&nbsp;<code>blacklist</code>&nbsp;的整数</li>
	<li><code>int pick()</code>&nbsp;返回一个范围为 <code>[0, n - 1]</code> 且不在黑名单&nbsp;<code>blacklist</code> 中的随机整数</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入</strong>
["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
[[7, [2, 3, 5]], [], [], [], [], [], [], []]
<strong>输出</strong>
[null, 0, 4, 1, 6, 1, 0, 4]

<b>解释
</b>Solution solution = new Solution(7, [2, 3, 5]);
solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，
                 // 0、1、4和6的返回概率必须相等(即概率为1/4)。
solution.pick(); // 返回 4
solution.pick(); // 返回 1
solution.pick(); // 返回 6
solution.pick(); // 返回 1
solution.pick(); // 返回 0
solution.pick(); // 返回 4
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= blacklist.length &lt;= min(10<sup>5</sup>, n - 1)</code></li>
	<li><code>0 &lt;= blacklist[i] &lt; n</code></li>
	<li><code>blacklist</code>&nbsp;中所有值都 <strong>不同</strong></li>
	<li>&nbsp;<code>pick</code>&nbsp;最多被调用&nbsp;<code>2 * 10<sup>4</sup></code>&nbsp;次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def __init__(self, n: int, blacklist: List[int]):
        self.k = n - len(blacklist)
        self.d = {}
        i = self.k
        black = set(blacklist)
        for b in blacklist:
            if b < self.k:
                while i in black:
                    i += 1
                self.d[b] = i
                i += 1

    def pick(self) -> int:
        x = randrange(self.k)
        return self.d.get(x, x)


# Your Solution object will be instantiated and called as such:
# obj = Solution(n, blacklist)
# param_1 = obj.pick()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Map<Integer, Integer> d = new HashMap<>();
    private Random rand = new Random();
    private int k;

    public Solution(int n, int[] blacklist) {
        k = n - blacklist.length;
        int i = k;
        Set<Integer> black = new HashSet<>();
        for (int b : blacklist) {
            black.add(b);
        }
        for (int b : blacklist) {
            if (b < k) {
                while (black.contains(i)) {
                    ++i;
                }
                d.put(b, i++);
            }
        }
    }

    public int pick() {
        int x = rand.nextInt(k);
        return d.getOrDefault(x, x);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */
```

### **C++**

```cpp
class Solution {
public:
    unordered_map<int, int> d;
    int k;

    Solution(int n, vector<int>& blacklist) {
        k = n - blacklist.size();
        int i = k;
        unordered_set<int> black(blacklist.begin(), blacklist.end());
        for (int& b : blacklist) {
            if (b < k) {
                while (black.count(i)) ++i;
                d[b] = i++;
            }
        }
    }

    int pick() {
        int x = rand() % k;
        return d.count(x) ? d[x] : x;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(n, blacklist);
 * int param_1 = obj->pick();
 */
```

### **Go**

```go
type Solution struct {
	d map[int]int
	k int
}

func Constructor(n int, blacklist []int) Solution {
	k := n - len(blacklist)
	i := k
	black := map[int]bool{}
	for _, b := range blacklist {
		black[b] = true
	}
	d := map[int]int{}
	for _, b := range blacklist {
		if b < k {
			for black[i] {
				i++
			}
			d[b] = i
			i++
		}
	}
	return Solution{d, k}
}

func (this *Solution) Pick() int {
	x := rand.Intn(this.k)
	if v, ok := this.d[x]; ok {
		return v
	}
	return x
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(n, blacklist);
 * param_1 := obj.Pick();
 */
```

### **...**

```

```

<!-- tabs:end -->
