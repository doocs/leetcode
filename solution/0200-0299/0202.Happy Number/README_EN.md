# [202. Happy Number](https://leetcode.com/problems/happy-number)

[中文文档](/solution/0200-0299/0202.Happy%20Number/README.md)

## Description

<p>Write an algorithm to determine if a number <code>n</code> is happy.</p>

<p>A <strong>happy number</strong> is a number defined by the following process:</p>

<ul>
	<li>Starting with any positive integer, replace the number by the sum of the squares of its digits.</li>
	<li>Repeat the process until the number equals 1 (where it will stay), or it <strong>loops endlessly in a cycle</strong> which does not include 1.</li>
	<li>Those numbers for which this process <strong>ends in 1</strong> are happy.</li>
</ul>

<p>Return <code>true</code> <em>if</em> <code>n</code> <em>is a happy number, and</em> <code>false</code> <em>if not</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 19
<strong>Output:</strong> true
<strong>Explanation:</strong>
1<sup>2</sup> + 9<sup>2</sup> = 82
8<sup>2</sup> + 2<sup>2</sup> = 68
6<sup>2</sup> + 8<sup>2</sup> = 100
1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isHappy(self, n: int) -> bool:
        vis = set()
        while n != 1 and n not in vis:
            vis.add(n)
            x = 0
            while n:
                n, v = divmod(n, 10)
                x += v * v
            n = x
        return n == 1
```

```python
class Solution:
    def isHappy(self, n: int) -> bool:
        def next(x):
            y = 0
            while x:
                x, v = divmod(x, 10)
                y += v * v
            return y

        slow, fast = n, next(n)
        while slow != fast:
            slow, fast = next(slow), next(next(fast))
        return slow == 1
```

### **Java**

```java
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> vis = new HashSet<>();
        while (n != 1 && !vis.contains(n)) {
            vis.add(n);
            int x = 0;
            while (n != 0) {
                x += (n % 10) * (n % 10);
                n /= 10;
            }
            n = x;
        }
        return n == 1;
    }
}
```

```java
class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = next(n);
        while (slow != fast) {
            slow = next(slow);
            fast = next(next(fast));
        }
        return slow == 1;
    }

    private int next(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y += (x % 10) * (x % 10);
        }
        return y;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isHappy(int n) {
        unordered_set<int> vis;
        while (n != 1 && !vis.count(n)) {
            vis.insert(n);
            int x = 0;
            for (; n; n /= 10) {
                x += (n % 10) * (n % 10);
            }
            n = x;
        }
        return n == 1;
    }
};
```

```cpp
class Solution {
public:
    bool isHappy(int n) {
        auto next = [](int x) {
            int y = 0;
            for (; x; x /= 10) {
                y += pow(x % 10, 2);
            }
            return y;
        };
        int slow = n, fast = next(n);
        while (slow != fast) {
            slow = next(slow);
            fast = next(next(fast));
        }
        return slow == 1;
    }
};
```

### **Go**

```go
func isHappy(n int) bool {
	vis := map[int]bool{}
	for n != 1 && !vis[n] {
		vis[n] = true
		x := 0
		for ; n > 0; n /= 10 {
			x += (n % 10) * (n % 10)
		}
		n = x
	}
	return n == 1
}
```

```go
func isHappy(n int) bool {
	next := func(x int) (y int) {
		for ; x > 0; x /= 10 {
			y += (x % 10) * (x % 10)
		}
		return
	}
	slow, fast := n, next(n)
	for slow != fast {
		slow = next(slow)
		fast = next(next(fast))
	}
	return slow == 1
}
```

### **TypeScript**

```ts
function isHappy(n: number): boolean {
    const getNext = (n: number) => {
        let res = 0;
        while (n !== 0) {
            res += (n % 10) ** 2;
            n = Math.floor(n / 10);
        }
        return res;
    };
    const set = new Set();
    while (n !== 1) {
        const next = getNext(n);
        if (set.has(next)) {
            return false;
        }
        set.add(next);
        n = next;
    }
    return true;
}
```

```ts
function isHappy(n: number): boolean {
    const getNext = (n: number) => {
        let res = 0;
        while (n !== 0) {
            res += (n % 10) ** 2;
            n = Math.floor(n / 10);
        }
        return res;
    };

    let slow = n;
    let fast = getNext(n);
    while (slow !== fast) {
        slow = getNext(slow);
        fast = getNext(getNext(fast));
    }
    return fast === 1;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    fn get_next(mut n: i32) -> i32 {
        let mut res = 0;
        while n != 0 {
            res += (n % 10).pow(2);
            n /= 10;
        }
        res
    }

    pub fn is_happy(mut n: i32) -> bool {
        let mut set = HashSet::new();
        while n != 1 {
            let next = Self::get_next(n);
            if set.contains(&next) {
                return false;
            }
            set.insert(next);
            n = next;
        }
        true
    }
}
```

```rust
impl Solution {
    pub fn is_happy(n: i32) -> bool {
        let get_next = |mut n: i32| {
            let mut res = 0;
            while n != 0 {
                res += (n % 10).pow(2);
                n /= 10;
            }
            res
        };
        let mut slow = n;
        let mut fast = get_next(n);
        while slow != fast {
            slow = get_next(slow);
            fast = get_next(get_next(fast));
        }
        slow == 1
    }
}
```

### **C**

```c
int getNext(int n) {
    int res = 0;
    while (n) {
        res += (n % 10) * (n % 10);
        n /= 10;
    }
    return res;
}

bool isHappy(int n) {
    int slow = n;
    int fast = getNext(n);
    while (slow != fast) {
        slow = getNext(slow);
        fast = getNext(getNext(fast));
    }
    return fast == 1;
}
```

### **...**

```

```

<!-- tabs:end -->
