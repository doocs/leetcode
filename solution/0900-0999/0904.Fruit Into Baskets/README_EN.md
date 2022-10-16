# [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets)

[中文文档](/solution/0900-0999/0904.Fruit%20Into%20Baskets/README.md)

## Description

<p>You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array <code>fruits</code> where <code>fruits[i]</code> is the <strong>type</strong> of fruit the <code>i<sup>th</sup></code> tree produces.</p>

<p>You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:</p>

<ul>
	<li>You only have <strong>two</strong> baskets, and each basket can only hold a <strong>single type</strong> of fruit. There is no limit on the amount of fruit each basket can hold.</li>
	<li>Starting from any tree of your choice, you must pick <strong>exactly one fruit</strong> from <strong>every</strong> tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.</li>
	<li>Once you reach a tree with fruit that cannot fit in your baskets, you must stop.</li>
</ul>

<p>Given the integer array <code>fruits</code>, return <em>the <strong>maximum</strong> number of fruits you can pick</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> fruits = [<u>1,2,1</u>]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can pick from all 3 trees.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> fruits = [0,<u>1,2,2</u>]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> fruits = [1,<u>2,3,2,2</u>]
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= fruits.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= fruits[i] &lt; fruits.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def totalFruit(self, fruits: List[int]) -> int:
        cnt = Counter()
        ans = j = 0
        for i, x in enumerate(fruits):
            cnt[x] += 1
            while len(cnt) > 2:
                y = fruits[j]
                cnt[y] -= 1
                if cnt[y] == 0:
                    cnt.pop(y)
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```python
class Solution:
    def totalFruit(self, fruits: List[int]) -> int:
        cnt = Counter()
        j = 0
        for x in fruits:
            cnt[x] += 1
            if len(cnt) > 2:
                y = fruits[j]
                cnt[y] -= 1
                if cnt[y] == 0:
                    cnt.pop(y)
                j += 1
        return len(fruits) - j
```

### **Java**

```java
class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; i < fruits.length; ++i) {
            int x = fruits[i];
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            while (cnt.size() > 2) {
                int y = fruits[j++];
                cnt.put(y, cnt.get(y) - 1);
                if (cnt.get(y) == 0) {
                    cnt.remove(y);
                }
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int j = 0, n = fruits.length;
        for (int x : fruits) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            if (cnt.size() > 2) {
                int y = fruits[j++];
                cnt.put(y, cnt.get(y) - 1);
                if (cnt.get(y) == 0) {
                    cnt.remove(y);
                }
            }
        }
        return n - j;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int totalFruit(vector<int>& fruits) {
        unordered_map<int, int> cnt;
        int ans = 0;
        for (int i = 0, j = 0; i < fruits.size(); ++i) {
            int x = fruits[i];
            ++cnt[x];
            while (cnt.size() > 2) {
                int y = fruits[j++];
                if (--cnt[y] == 0) cnt.erase(y);
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int totalFruit(vector<int>& fruits) {
        unordered_map<int, int> cnt;
        int j = 0, n = fruits.size();
        for (int& x : fruits) {
            ++cnt[x];
            if (cnt.size() > 2) {
                int y = fruits[j++];
                if (--cnt[y] == 0) cnt.erase(y);
            }
        }
        return n - j;
    }
};
```

### **Go**

```go
func totalFruit(fruits []int) int {
	cnt := map[int]int{}
	ans, j := 0, 0
	for i, x := range fruits {
		cnt[x]++
		for ; len(cnt) > 2; j++ {
			y := fruits[j]
			cnt[y]--
			if cnt[y] == 0 {
				delete(cnt, y)
			}
		}
		ans = max(ans, i-j+1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func totalFruit(fruits []int) int {
	cnt := map[int]int{}
	j := 0
	for _, x := range fruits {
		cnt[x]++
		if len(cnt) > 2 {
			y := fruits[j]
			cnt[y]--
			if cnt[y] == 0 {
				delete(cnt, y)
			}
			j++
		}
	}
	return len(fruits) - j
}
```

### **TypeScript**

```ts
function totalFruit(fruits: number[]): number {
    const n = fruits.length;
    const map = new Map<number, number>();
    let res = 0;
    let left = 0;
    let right = 0;
    while (right < n) {
        map.set(fruits[right], (map.get(fruits[right]) ?? 0) + 1);
        right++;
        while (map.size > 2) {
            const k = fruits[left++];
            map.set(k, map.get(k) - 1);
            if (map.get(k) === 0) {
                map.delete(k);
            }
        }
        res = Math.max(res, right - left);
    }
    return res;
}
```

```ts
function totalFruit(fruits: number[]): number {
    const n = fruits.length;
    const map = new Map<number, number>();
    let i = 0;
    for (const fruit of fruits) {
        map.set(fruit, (map.get(fruit) ?? 0) + 1);
        if (map.size > 2) {
            const k = fruits[i++];
            map.set(k, map.get(k) - 1);
            if (map.get(k) == 0) {
                map.delete(k);
            }
        }
    }
    return n - i;
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn total_fruit(fruits: Vec<i32>) -> i32 {
        let n = fruits.len();
        let mut map = HashMap::new();
        let mut res = 0;
        let mut left = 0;
        let mut right = 0;
        while right < n {
            *map.entry(fruits[right]).or_insert(0) += 1;
            right += 1;
            while map.len() > 2 {
                let k = fruits[left];
                map.insert(k, map[&k] - 1);
                if map[&k] == 0 {
                    map.remove(&k);
                }
                left += 1;
            }
            res = res.max(right - left);
        }
        res as i32
    }
}
```

```rust
use std::collections::HashMap;
impl Solution {
    pub fn total_fruit(fruits: Vec<i32>) -> i32 {
        let n = fruits.len();
        let mut map = HashMap::new();
        let mut i = 0;
        for &fruit in fruits.iter() {
            *map.entry(fruit).or_insert(0) += 1;
            if map.len() > 2 {
                let k = fruits[i];
                map.insert(k, map[&k] - 1);
                if map[&k] == 0 {
                    map.remove(&k);
                }
                i += 1;
            }
        }
        (n - i) as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
