---
comments: true
difficulty: Easy
tags:
    - Hash Table
    - Math
    - Two Pointers
    - Floyd Cycle Detection
---

<!-- problem:start -->

# [202. Happy Number](https://leetcode.com/problems/happy-number)

[中文文档](/solution/0200-0299/0202.Happy%20Number/README.md)

## Mô tả

<!-- description:start -->

<p>Viết một thuật toán để xác định xem một số <code>n</code> có phải là số hạnh phúc hay không.</p>

<p><strong>Số hạnh phúc</strong> là một số được định nghĩa bởi quy trình sau:</p>

<ul>
	<li>Bắt đầu với một số nguyên dương bất kỳ, thay số đó bằng tổng bình phương các chữ số của nó.</li>
	<li>Lặp lại quy trình cho đến khi số đó bằng 1 (khi đó nó sẽ giữ nguyên), hoặc <strong>lặp vô hạn trong một chu kỳ</strong> không chứa 1.</li>
	<li>Những số mà quy trình này <strong>kết thúc ở 1</strong> là số hạnh phúc.</li>
</ul>

<p>Trả về <code>true</code> <em>nếu</em> <code>n</code> <em>là một số hạnh phúc, và</em> <code>false</code> <em>nếu không phải</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 19
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong>
1<sup>2</sup> + 9<sup>2</sup> = 82
8<sup>2</sup> + 2<sup>2</sup> = 68
6<sup>2</sup> + 8<sup>2</sup> = 100
1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 2
<strong>Đầu ra:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Việc liên tục thay một số bằng tổng bình phương các chữ số của nó tuân theo định nghĩa, nhưng dãy số có thể lặp lại thay vì đạt đến $1$. Các giá trị nhanh chóng rơi vào một phạm vi bị chặn, vì vậy có thể dùng một hash set để ghi lại các số đã gặp.
>
> Một giá trị lặp lại nghĩa là đã có chu kỳ (không phải số hạnh phúc); đạt đến $1$ nghĩa là số đó là số hạnh phúc. Mô phỏng và kiểm tra chu kỳ được thực hiện đồng thời.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### Rust

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

#### C

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Hash set là đúng nhưng sử dụng thêm bộ nhớ. Ánh xạ tổng bình phương là một phép lặp xác định, vì vậy có thể tìm chu kỳ mà không cần lưu toàn bộ lịch sử.
>
> Chỉ cần dùng hai con trỏ của Floyd: con trỏ chậm di chuyển một lần, còn con trỏ nhanh di chuyển hai lần; nếu chúng gặp nhau tại $1$, số đó là số hạnh phúc, và mức sử dụng bộ nhớ trở thành hằng số.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### Rust

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
