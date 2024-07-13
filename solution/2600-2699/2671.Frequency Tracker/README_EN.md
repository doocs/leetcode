---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2671.Frequency%20Tracker/README_EN.md
rating: 1509
source: Weekly Contest 344 Q2
tags:
    - Design
    - Hash Table
---

<!-- problem:start -->

# [2671. Frequency Tracker](https://leetcode.com/problems/frequency-tracker)

[中文文档](/solution/2600-2699/2671.Frequency%20Tracker/README.md)

## Description

<!-- description:start -->

<p>Design a data structure that keeps track of the values in it and answers some queries regarding their frequencies.</p>

<p>Implement the <code>FrequencyTracker</code> class.</p>

<ul>
	<li><code>FrequencyTracker()</code>: Initializes the <code>FrequencyTracker</code> object with an empty array initially.</li>
	<li><code>void add(int number)</code>: Adds <code>number</code> to the data structure.</li>
	<li><code>void deleteOne(int number)</code>: Deletes <strong>one</strong> occurrence of <code>number</code> from the data structure. The data structure <strong>may not contain</strong> <code>number</code>, and in this case nothing is deleted.</li>
	<li><code>bool hasFrequency(int frequency)</code>: Returns <code>true</code> if there is a number in the data structure that occurs <code>frequency</code> number of times, otherwise, it returns <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;FrequencyTracker&quot;, &quot;add&quot;, &quot;add&quot;, &quot;hasFrequency&quot;]
[[], [3], [3], [2]]
<strong>Output</strong>
[null, null, null, true]

<strong>Explanation</strong>
FrequencyTracker frequencyTracker = new FrequencyTracker();
frequencyTracker.add(3); // The data structure now contains [3]
frequencyTracker.add(3); // The data structure now contains [3, 3]
frequencyTracker.hasFrequency(2); // Returns true, because 3 occurs twice

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input</strong>
[&quot;FrequencyTracker&quot;, &quot;add&quot;, &quot;deleteOne&quot;, &quot;hasFrequency&quot;]
[[], [1], [1], [1]]
<strong>Output</strong>
[null, null, null, false]

<strong>Explanation</strong>
FrequencyTracker frequencyTracker = new FrequencyTracker();
frequencyTracker.add(1); // The data structure now contains [1]
frequencyTracker.deleteOne(1); // The data structure becomes empty []
frequencyTracker.hasFrequency(1); // Returns false, because the data structure is empty

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input</strong>
[&quot;FrequencyTracker&quot;, &quot;hasFrequency&quot;, &quot;add&quot;, &quot;hasFrequency&quot;]
[[], [2], [3], [1]]
<strong>Output</strong>
[null, false, null, true]

<strong>Explanation</strong>
FrequencyTracker frequencyTracker = new FrequencyTracker();
frequencyTracker.hasFrequency(2); // Returns false, because the data structure is empty
frequencyTracker.add(3); // The data structure now contains [3]
frequencyTracker.hasFrequency(1); // Returns true, because 3 occurs once

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= number &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= frequency &lt;= 10<sup>5</sup></code></li>
	<li>At most, <code>2 *&nbsp;10<sup>5</sup></code>&nbsp;calls will be made to <code>add</code>, <code>deleteOne</code>, and <code>hasFrequency</code>&nbsp;in <strong>total</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We define two hash tables, where $cnt$ is used to record the occurrence count of each number, and $freq$ is used to record the count of numbers with each frequency.

For the `add` operation, we directly decrement the value corresponding to $cnt[number]$ in the hash table $freq$, then increment $cnt[number]$, and finally increment the value corresponding to $cnt[number]$ in $freq$.

For the `deleteOne` operation, we first check if $cnt[number]$ is greater than zero. If it is, we decrement the value corresponding to $cnt[number]$ in the hash table $freq$, then decrement $cnt[number]$, and finally increment the value corresponding to $cnt[number]$ in $freq$.

For the `hasFrequency` operation, we directly return whether $freq[frequency]$ is greater than zero.

In terms of time complexity, since we use hash tables, the time complexity of each operation is $O(1)$. The space complexity is $O(n)$, where $n$ is the number of distinct numbers.

<!-- tabs:start -->

#### Python3

```python
class FrequencyTracker:
    def __init__(self):
        self.cnt = defaultdict(int)
        self.freq = defaultdict(int)

    def add(self, number: int) -> None:
        self.freq[self.cnt[number]] -= 1
        self.cnt[number] += 1
        self.freq[self.cnt[number]] += 1

    def deleteOne(self, number: int) -> None:
        if self.cnt[number]:
            self.freq[self.cnt[number]] -= 1
            self.cnt[number] -= 1
            self.freq[self.cnt[number]] += 1

    def hasFrequency(self, frequency: int) -> bool:
        return self.freq[frequency] > 0


# Your FrequencyTracker object will be instantiated and called as such:
# obj = FrequencyTracker()
# obj.add(number)
# obj.deleteOne(number)
# param_3 = obj.hasFrequency(frequency)
```

#### Java

```java
class FrequencyTracker {
    private Map<Integer, Integer> cnt = new HashMap<>();
    private Map<Integer, Integer> freq = new HashMap<>();

    public FrequencyTracker() {
    }

    public void add(int number) {
        freq.merge(cnt.getOrDefault(number, 0), -1, Integer::sum);
        cnt.merge(number, 1, Integer::sum);
        freq.merge(cnt.get(number), 1, Integer::sum);
    }

    public void deleteOne(int number) {
        if (cnt.getOrDefault(number, 0) > 0) {
            freq.merge(cnt.get(number), -1, Integer::sum);
            cnt.merge(number, -1, Integer::sum);
            freq.merge(cnt.get(number), 1, Integer::sum);
        }
    }

    public boolean hasFrequency(int frequency) {
        return freq.getOrDefault(frequency, 0) > 0;
    }
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * FrequencyTracker obj = new FrequencyTracker();
 * obj.add(number);
 * obj.deleteOne(number);
 * boolean param_3 = obj.hasFrequency(frequency);
 */
```

#### C++

```cpp
class FrequencyTracker {
public:
    FrequencyTracker() {
    }

    void add(int number) {
        freq[cnt[number]]--;
        cnt[number]++;
        freq[cnt[number]]++;
    }

    void deleteOne(int number) {
        if (cnt[number]) {
            freq[cnt[number]]--;
            cnt[number]--;
            freq[cnt[number]]++;
        }
    }

    bool hasFrequency(int frequency) {
        return freq[frequency] > 0;
    }

private:
    unordered_map<int, int> cnt;
    unordered_map<int, int> freq;
};

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * FrequencyTracker* obj = new FrequencyTracker();
 * obj->add(number);
 * obj->deleteOne(number);
 * bool param_3 = obj->hasFrequency(frequency);
 */
```

#### Go

```go
type FrequencyTracker struct {
	cnt  map[int]int
	freq map[int]int
}

func Constructor() FrequencyTracker {
	return FrequencyTracker{map[int]int{}, map[int]int{}}
}

func (this *FrequencyTracker) Add(number int) {
	this.freq[this.cnt[number]]--
	this.cnt[number]++
	this.freq[this.cnt[number]]++
}

func (this *FrequencyTracker) DeleteOne(number int) {
	if this.cnt[number] > 0 {
		this.freq[this.cnt[number]]--
		this.cnt[number]--
		this.freq[this.cnt[number]]++
	}
}

func (this *FrequencyTracker) HasFrequency(frequency int) bool {
	return this.freq[frequency] > 0
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(number);
 * obj.DeleteOne(number);
 * param_3 := obj.HasFrequency(frequency);
 */
```

#### TypeScript

```ts
class FrequencyTracker {
    private cnt: Map<number, number>;
    private freq: Map<number, number>;

    constructor() {
        this.cnt = new Map();
        this.freq = new Map();
    }

    add(number: number): void {
        const f = this.cnt.get(number) || 0;
        this.freq.set(f, (this.freq.get(f) || 0) - 1);
        this.cnt.set(number, f + 1);
        this.freq.set(f + 1, (this.freq.get(f + 1) || 0) + 1);
    }

    deleteOne(number: number): void {
        const f = this.cnt.get(number) || 0;
        if (f > 0) {
            this.freq.set(f, (this.freq.get(f) || 0) - 1);
            this.cnt.set(number, f - 1);
            this.freq.set(f - 1, (this.freq.get(f - 1) || 0) + 1);
        }
    }

    hasFrequency(frequency: number): boolean {
        return (this.freq.get(frequency) || 0) > 0;
    }
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * var obj = new FrequencyTracker()
 * obj.add(number)
 * obj.deleteOne(number)
 * var param_3 = obj.hasFrequency(frequency)
 */
```

#### Rust

```rust
use std::collections::HashMap;

struct FrequencyTracker {
    cnt: HashMap<i32, i32>,
    freq: HashMap<i32, i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl FrequencyTracker {
    fn new() -> Self {
        FrequencyTracker {
            cnt: HashMap::new(),
            freq: HashMap::new(),
        }
    }

    fn add(&mut self, number: i32) {
        let count = self.cnt.entry(number).or_insert(0);
        let prev_freq = self.freq.entry(*count).or_insert(0);
        *prev_freq -= 1;
        *count += 1;
        let new_freq = self.freq.entry(*count).or_insert(0);
        *new_freq += 1;
    }

    fn delete_one(&mut self, number: i32) {
        if let Some(count) = self.cnt.get_mut(&number) {
            if *count > 0 {
                if let Some(prev_freq) = self.freq.get_mut(count) {
                    *prev_freq -= 1;
                }
                *count -= 1;
                if let Some(new_freq) = self.freq.get_mut(count) {
                    *new_freq += 1;
                }
            }
        }
    }

    fn has_frequency(&self, frequency: i32) -> bool {
        self.freq.get(&frequency).map_or(false, |&freq| freq > 0)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
