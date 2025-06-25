---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2424.Longest%20Uploaded%20Prefix/README_EN.md
rating: 1604
source: Biweekly Contest 88 Q2
tags:
    - Union Find
    - Design
    - Binary Indexed Tree
    - Segment Tree
    - Hash Table
    - Binary Search
    - Ordered Set
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2424. Longest Uploaded Prefix](https://leetcode.com/problems/longest-uploaded-prefix)

[中文文档](/solution/2400-2499/2424.Longest%20Uploaded%20Prefix/README.md)

## Description

<!-- description:start -->

<p>You are given a stream of <code>n</code> videos, each represented by a <strong>distinct</strong> number from <code>1</code> to <code>n</code> that you need to &quot;upload&quot; to a server. You need to implement a data structure that calculates the length of the <strong>longest uploaded prefix</strong> at various points in the upload process.</p>

<p>We consider <code>i</code> to be an uploaded prefix if all videos in the range <code>1</code> to <code>i</code> (<strong>inclusive</strong>) have been uploaded to the server. The longest uploaded prefix is the <strong>maximum </strong>value of <code>i</code> that satisfies this definition.<br />
<br />
Implement the <code>LUPrefix </code>class:</p>

<ul>
	<li><code>LUPrefix(int n)</code> Initializes the object for a stream of <code>n</code> videos.</li>
	<li><code>void upload(int video)</code> Uploads <code>video</code> to the server.</li>
	<li><code>int longest()</code> Returns the length of the <strong>longest uploaded prefix</strong> defined above.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;LUPrefix&quot;, &quot;upload&quot;, &quot;longest&quot;, &quot;upload&quot;, &quot;longest&quot;, &quot;upload&quot;, &quot;longest&quot;]
[[4], [3], [], [1], [], [2], []]
<strong>Output</strong>
[null, null, 0, null, 1, null, 3]

<strong>Explanation</strong>
LUPrefix server = new LUPrefix(4);   // Initialize a stream of 4 videos.
server.upload(3);                    // Upload video 3.
server.longest();                    // Since video 1 has not been uploaded yet, there is no prefix.
                                     // So, we return 0.
server.upload(1);                    // Upload video 1.
server.longest();                    // The prefix [1] is the longest uploaded prefix, so we return 1.
server.upload(2);                    // Upload video 2.
server.longest();                    // The prefix [1,2,3] is the longest uploaded prefix, so we return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= video &lt;= n</code></li>
	<li>All values of <code>video</code> are <strong>distinct</strong>.</li>
	<li>At most <code>2 * 10<sup>5</sup></code> calls <strong>in total</strong> will be made to <code>upload</code> and <code>longest</code>.</li>
	<li>At least one call will be made to <code>longest</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use a variable $r$ to record the current longest prefix of uploaded videos, and an array or hash table $s$ to record the videos that have been uploaded.

Each time a video is uploaded, we set `s[video]` to `true`, then loop to check whether `s[r + 1]` is `true`. If it is, we update $r$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the total number of videos.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
