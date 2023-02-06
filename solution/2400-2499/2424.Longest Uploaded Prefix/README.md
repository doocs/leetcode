# [2424. 最长上传前缀](https://leetcode.cn/problems/longest-uploaded-prefix)

[English Version](/solution/2400-2499/2424.Longest%20Uploaded%20Prefix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>n</code>&nbsp;个视频的上传序列，每个视频编号为&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;之间的 <strong>不同</strong>&nbsp;数字，你需要依次将这些视频上传到服务器。请你实现一个数据结构，在上传的过程中计算 <strong>最长上传前缀</strong>&nbsp;。</p>

<p>如果&nbsp;<strong>闭区间</strong>&nbsp;<code>1</code>&nbsp;到&nbsp;<code>i</code>&nbsp;之间的视频全部都已经被上传到服务器，那么我们称 <code>i</code>&nbsp;是上传前缀。最长上传前缀指的是符合定义的 <code>i</code>&nbsp;中的 <strong>最大值</strong>&nbsp;。<br>
<br>
请你实现&nbsp;<code>LUPrefix</code>&nbsp;类：</p>

<ul>
	<li><code>LUPrefix(int n)</code>&nbsp;初始化一个 <code>n</code>&nbsp;个视频的流对象。</li>
	<li><code>void upload(int video)</code>&nbsp;上传&nbsp;<code>video</code>&nbsp;到服务器。</li>
	<li><code>int longest()</code>&nbsp;返回上述定义的 <strong>最长上传前缀</strong>&nbsp;的长度。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
["LUPrefix", "upload", "longest", "upload", "longest", "upload", "longest"]
[[4], [3], [], [1], [], [2], []]
<strong>输出：</strong>
[null, null, 0, null, 1, null, 3]

<strong>解释：</strong>
LUPrefix server = new LUPrefix(4);   // 初始化 4个视频的上传流
server.upload(3);                    // 上传视频 3 。
server.longest();                    // 由于视频 1 还没有被上传，最长上传前缀是 0 。
server.upload(1);                    // 上传视频 1 。
server.longest();                    // 前缀 [1] 是最长上传前缀，所以我们返回 1 。
server.upload(2);                    // 上传视频 2 。
server.longest();                    // 前缀 [1,2,3] 是最长上传前缀，所以我们返回 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= video &lt;= 10<sup>5</sup></code></li>
	<li><code>video</code>&nbsp;中所有值 <strong>互不相同</strong>&nbsp;。</li>
	<li><code>upload</code> 和&nbsp;<code>longest</code>&nbsp;<strong>总调用</strong> 次数至多不超过&nbsp;<code>2 * 10<sup>5</sup></code>&nbsp;次。</li>
	<li>至少会调用&nbsp;<code>longest</code>&nbsp;一次。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们用变量 $r$ 记录当前的最长上传前缀，用数组或哈希表 $s$ 记录已经上传的视频。

每次上传视频 `video` 时，将 `s[video]` 置为 `true`，然后循环判断 `s[r + 1]` 是否为 `true`，如果是，则更新 $r$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为视频总数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class LUPrefix:
    def __init__(self, n: int):
        self.r = 0
        self.s = set()

    def upload(self, video: int) -> None:
        self.s.add(video)
        while self.r + 1 in self.s:
            self.r += 1

    def longest(self) -> int:
        return self.r


# Your LUPrefix object will be instantiated and called as such:
# obj = LUPrefix(n)
# obj.upload(video)
# param_2 = obj.longest()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class LUPrefix {
    private int r;
    private Set<Integer> s = new HashSet<>();

    public LUPrefix(int n) {
    }

    public void upload(int video) {
        s.add(video);
        while (s.contains(r + 1)) {
            ++r;
        }
    }

    public int longest() {
        return r;
    }
}

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix obj = new LUPrefix(n);
 * obj.upload(video);
 * int param_2 = obj.longest();
 */
```

### **C++**

```cpp
class LUPrefix {
public:
    LUPrefix(int n) {

    }

    void upload(int video) {
        s.insert(video);
        while (s.count(r + 1)) {
            ++r;
        }
    }

    int longest() {
        return r;
    }

private:
    int r = 0;
    unordered_set<int> s;
};

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix* obj = new LUPrefix(n);
 * obj->upload(video);
 * int param_2 = obj->longest();
 */
```

### **Go**

```go
type LUPrefix struct {
	r int
	s []bool
}

func Constructor(n int) LUPrefix {
	return LUPrefix{0, make([]bool, n+1)}
}

func (this *LUPrefix) Upload(video int) {
	this.s[video] = true
	for this.r+1 < len(this.s) && this.s[this.r+1] {
		this.r++
	}
}

func (this *LUPrefix) Longest() int {
	return this.r
}

/**
 * Your LUPrefix object will be instantiated and called as such:
 * obj := Constructor(n);
 * obj.Upload(video);
 * param_2 := obj.Longest();
 */
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
