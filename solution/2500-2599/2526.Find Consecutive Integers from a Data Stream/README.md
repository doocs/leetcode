---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2526.Find%20Consecutive%20Integers%20from%20a%20Data%20Stream/README.md
rating: 1444
source: 第 95 场双周赛 Q2
tags:
    - 设计
    - 队列
    - 哈希表
    - 计数
    - 数据流
---

<!-- problem:start -->

# [2526. 找到数据流中的连续整数](https://leetcode.cn/problems/find-consecutive-integers-from-a-data-stream)

[English Version](/solution/2500-2599/2526.Find%20Consecutive%20Integers%20from%20a%20Data%20Stream/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数据流，请你实现一个数据结构，检查数据流中最后&nbsp;<code>k</code>&nbsp;个整数是否 <strong>等于</strong> 给定值&nbsp;<code>value</code>&nbsp;。</p>

<p>请你实现&nbsp;<strong>DataStream</strong>&nbsp;类：</p>

<ul>
	<li><code>DataStream(int value, int k)</code>&nbsp;用两个整数 <code>value</code>&nbsp;和 <code>k</code>&nbsp;初始化一个空的整数数据流。</li>
	<li><code>boolean consec(int num)</code>&nbsp;将&nbsp;<code>num</code>&nbsp;添加到整数数据流。如果后 <code>k</code>&nbsp;个整数都等于&nbsp;<code>value</code>&nbsp;，返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。如果少于&nbsp;<code>k</code>&nbsp;个整数，条件不满足，所以也返回&nbsp;<code>false</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["DataStream", "consec", "consec", "consec", "consec"]
[[4, 3], [4], [4], [4], [3]]
<strong>输出：</strong>
[null, false, false, true, false]

<strong>解释：</strong>
DataStream dataStream = new DataStream(4, 3); // value = 4, k = 3 
dataStream.consec(4); // 数据流中只有 1 个整数，所以返回 False 。
dataStream.consec(4); // 数据流中只有 2 个整数
                      // 由于 2 小于 k ，返回 False 。
dataStream.consec(4); // 数据流最后 3 个整数都等于 value， 所以返回 True 。
dataStream.consec(3); // 最后 k 个整数分别是 [4,4,3] 。
                      // 由于 3 不等于 value ，返回 False 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= value, num &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li>至多调用 <code>consec</code>&nbsp;次数为&nbsp;<code>10<sup>5</sup></code>&nbsp;次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以维护一个计数器 $\textit{cnt}$，记录当前连续整数为 $\textit{value}$ 的个数。

调用 `consec` 方法时，如果 $\textit{num}$ 与 $\textit{value}$ 相等，我们将 $\textit{cnt}$ 自增 1，否则将 $\textit{cnt}$ 重置为 0。然后判断 $\textit{cnt}$ 是否大于等于 $\textit{k}$ 即可。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class DataStream:
    def __init__(self, value: int, k: int):
        self.val, self.k = value, k
        self.cnt = 0

    def consec(self, num: int) -> bool:
        self.cnt = 0 if num != self.val else self.cnt + 1
        return self.cnt >= self.k


# Your DataStream object will be instantiated and called as such:
# obj = DataStream(value, k)
# param_1 = obj.consec(num)
```

#### Java

```java
class DataStream {
    private int cnt;
    private int val;
    private int k;

    public DataStream(int value, int k) {
        val = value;
        this.k = k;
    }

    public boolean consec(int num) {
        cnt = num == val ? cnt + 1 : 0;
        return cnt >= k;
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream obj = new DataStream(value, k);
 * boolean param_1 = obj.consec(num);
 */
```

#### C++

```cpp
class DataStream {
public:
    DataStream(int value, int k) {
        val = value;
        this->k = k;
    }

    bool consec(int num) {
        cnt = num == val ? cnt + 1 : 0;
        return cnt >= k;
    }

private:
    int cnt = 0;
    int val, k;
};

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream* obj = new DataStream(value, k);
 * bool param_1 = obj->consec(num);
 */
```

#### Go

```go
type DataStream struct {
	val, k, cnt int
}

func Constructor(value int, k int) DataStream {
	return DataStream{value, k, 0}
}

func (this *DataStream) Consec(num int) bool {
	if num == this.val {
		this.cnt++
	} else {
		this.cnt = 0
	}
	return this.cnt >= this.k
}

/**
 * Your DataStream object will be instantiated and called as such:
 * obj := Constructor(value, k);
 * param_1 := obj.Consec(num);
 */
```

#### TypeScript

```ts
class DataStream {
    private val: number;
    private k: number;
    private cnt: number;

    constructor(value: number, k: number) {
        this.val = value;
        this.k = k;
        this.cnt = 0;
    }

    consec(num: number): boolean {
        this.cnt = this.val === num ? this.cnt + 1 : 0;
        return this.cnt >= this.k;
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * var obj = new DataStream(value, k)
 * var param_1 = obj.consec(num)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
