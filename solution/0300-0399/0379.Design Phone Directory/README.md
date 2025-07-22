---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0379.Design%20Phone%20Directory/README.md
tags:
    - 设计
    - 队列
    - 数组
    - 哈希表
    - 链表
---

<!-- problem:start -->

# [379. 电话目录管理系统 🔒](https://leetcode.cn/problems/design-phone-directory)

[English Version](/solution/0300-0399/0379.Design%20Phone%20Directory/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计一个电话目录管理系统，一开始有&nbsp;<code>maxNumbers</code>&nbsp;个位置能够储存号码。系统应该存储号码，检查某个位置是否为空，并清空给定的位置。</p>

<p>实现&nbsp;<code>PhoneDirectory</code>&nbsp;类：</p>

<ul>
	<li><code>PhoneDirectory(int maxNumbers)</code>&nbsp;电话目录初始有 <code>maxNumbers</code> 个可用位置。</li>
	<li><code>int get()</code> 提供一个未分配给任何人的号码。如果没有可用号码则返回&nbsp;<code>-1</code>。</li>
	<li><code>bool check(int number)</code>&nbsp;如果位置&nbsp;<code>number</code>&nbsp;可用返回 <code>true</code>&nbsp;否则返回&nbsp;<code>false</code>。</li>
	<li><code>void release(int number)</code> 回收或释放位置&nbsp;<code>number</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["PhoneDirectory", "get", "get", "check", "get", "check", "release", "check"]
[[3], [], [], [2], [], [2], [2], [2]]
<strong>输出：</strong>
[null, 0, 1, true, 2, false, null, true]

<strong>解释：</strong>
PhoneDirectory phoneDirectory = new PhoneDirectory(3);
phoneDirectory.get();      // 它可以返回任意可用的数字。这里我们假设它返回 0。
phoneDirectory.get();      // 假设它返回 1。
phoneDirectory.check(2);   // 数字 2 可用，所以返回 true。
phoneDirectory.get();      // 返回剩下的唯一一个数字 2。
phoneDirectory.check(2);   // 数字 2 不再可用，所以返回 false。
phoneDirectory.release(2); // 将数字 2 释放回号码池。
phoneDirectory.check(2);   // 数字 2 重新可用，返回 true。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= maxNumbers &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= number &lt; maxNumbers</code></li>
	<li><code>get</code>，<code>check</code>&nbsp;和&nbsp;<code>release</code>&nbsp;最多被调用&nbsp;<code>2 * 10<sup>4</sup></code>&nbsp;次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以使用一个哈希集合 `available` 来存储未被分配的电话号码，初始时，哈希表中存储的是 `[0, 1, 2, ..., maxNumbers - 1]`。

调用 `get` 方法时，我们从 `available` 中取出一个未被分配的电话号码，如果 `available` 为空，则返回 `-1`。时间复杂度 $O(1)$。

调用 `check` 方法时，我们只需要判断 `number` 是否在 `available` 中即可。时间复杂度 $O(1)$。

调用 `release` 方法时，我们将 `number` 添加到 `available` 中。时间复杂度 $O(1)$。

空间复杂度 $O(n)$，其中 $n$ 是 `maxNumbers` 的值。

<!-- tabs:start -->

#### Python3

```python
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.available = set(range(maxNumbers))

    def get(self) -> int:
        if not self.available:
            return -1
        return self.available.pop()

    def check(self, number: int) -> bool:
        return number in self.available

    def release(self, number: int) -> None:
        self.available.add(number)


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
```

#### Java

```java
class PhoneDirectory {
    private Set<Integer> available = new HashSet<>();

    public PhoneDirectory(int maxNumbers) {
        for (int i = 0; i < maxNumbers; ++i) {
            available.add(i);
        }
    }

    public int get() {
        if (available.isEmpty()) {
            return -1;
        }
        int x = available.iterator().next();
        available.remove(x);
        return x;
    }

    public boolean check(int number) {
        return available.contains(number);
    }

    public void release(int number) {
        available.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
```

#### C++

```cpp
class PhoneDirectory {
public:
    PhoneDirectory(int maxNumbers) {
        for (int i = 0; i < maxNumbers; ++i) {
            available.insert(i);
        }
    }

    int get() {
        if (available.empty()) {
            return -1;
        }
        int x = *available.begin();
        available.erase(x);
        return x;
    }

    bool check(int number) {
        return available.contains(number);
    }

    void release(int number) {
        available.insert(number);
    }

private:
    unordered_set<int> available;
};

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory* obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj->get();
 * bool param_2 = obj->check(number);
 * obj->release(number);
 */
```

#### Go

```go
type PhoneDirectory struct {
	available map[int]bool
}

func Constructor(maxNumbers int) PhoneDirectory {
	available := make(map[int]bool)
	for i := 0; i < maxNumbers; i++ {
		available[i] = true
	}
	return PhoneDirectory{available}
}

func (this *PhoneDirectory) Get() int {
	for k := range this.available {
		delete(this.available, k)
		return k
	}
	return -1
}

func (this *PhoneDirectory) Check(number int) bool {
	_, ok := this.available[number]
	return ok
}

func (this *PhoneDirectory) Release(number int) {
	this.available[number] = true
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * obj := Constructor(maxNumbers);
 * param_1 := obj.Get();
 * param_2 := obj.Check(number);
 * obj.Release(number);
 */
```

#### TypeScript

```ts
class PhoneDirectory {
    private available: Set<number> = new Set();

    constructor(maxNumbers: number) {
        for (let i = 0; i < maxNumbers; ++i) {
            this.available.add(i);
        }
    }

    get(): number {
        const [x] = this.available;
        if (x === undefined) {
            return -1;
        }
        this.available.delete(x);
        return x;
    }

    check(number: number): boolean {
        return this.available.has(number);
    }

    release(number: number): void {
        this.available.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * var obj = new PhoneDirectory(maxNumbers)
 * var param_1 = obj.get()
 * var param_2 = obj.check(number)
 * obj.release(number)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
