---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0379.Design%20Phone%20Directory/README_EN.md
tags:
    - Design
    - Queue
    - Array
    - Hash Table
    - Linked List
---

<!-- problem:start -->

# [379. Design Phone Directory 🔒](https://leetcode.com/problems/design-phone-directory)

[中文文档](/solution/0300-0399/0379.Design%20Phone%20Directory/README.md)

## Description

<!-- description:start -->

<p>Design a phone directory that initially has <code>maxNumbers</code> empty slots that can store numbers. The directory should store numbers, check if a certain slot is empty or not, and empty a given slot.</p>

<p>Implement the <code>PhoneDirectory</code> class:</p>

<ul>
	<li><code>PhoneDirectory(int maxNumbers)</code> Initializes the phone directory with the number of available slots <code>maxNumbers</code>.</li>
	<li><code>int get()</code> Provides a number that is not assigned to anyone. Returns <code>-1</code> if no number is available.</li>
	<li><code>bool check(int number)</code> Returns <code>true</code> if the slot <code>number</code> is available and <code>false</code> otherwise.</li>
	<li><code>void release(int number)</code> Recycles or releases the slot <code>number</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;PhoneDirectory&quot;, &quot;get&quot;, &quot;get&quot;, &quot;check&quot;, &quot;get&quot;, &quot;check&quot;, &quot;release&quot;, &quot;check&quot;]
[[3], [], [], [2], [], [2], [2], [2]]
<strong>Output</strong>
[null, 0, 1, true, 2, false, null, true]

<strong>Explanation</strong>
PhoneDirectory phoneDirectory = new PhoneDirectory(3);
phoneDirectory.get();      // It can return any available phone number. Here we assume it returns 0.
phoneDirectory.get();      // Assume it returns 1.
phoneDirectory.check(2);   // The number 2 is available, so return true.
phoneDirectory.get();      // It returns 2, the only number that is left.
phoneDirectory.check(2);   // The number 2 is no longer available, so return false.
phoneDirectory.release(2); // Release number 2 back to the pool.
phoneDirectory.check(2);   // Number 2 is available again, return true.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= maxNumbers &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= number &lt; maxNumbers</code></li>
	<li>At most <code>2 * 10<sup>4</sup></code> calls will be made to <code>get</code>, <code>check</code>, and <code>release</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash set `available` to store unallocated phone numbers. Initially, the hash set contains `[0, 1, 2, ..., maxNumbers - 1]`.

When the `get` method is called, we take an unallocated phone number from `available`. If `available` is empty, we return `-1`. The time complexity is $O(1)$.

When the `check` method is called, we just need to check whether `number` is in `available`. The time complexity is $O(1)$.

When the `release` method is called, we add `number` to `available`. The time complexity is $O(1)$.

The space complexity is $O(n)$, where $n$ is the value of `maxNumbers`.

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
