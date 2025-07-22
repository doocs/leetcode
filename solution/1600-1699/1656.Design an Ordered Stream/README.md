---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1656.Design%20an%20Ordered%20Stream/README.md
rating: 1418
source: 第 215 场周赛 Q1
tags:
    - 设计
    - 数组
    - 哈希表
    - 数据流
---

<!-- problem:start -->

# [1656. 设计有序流](https://leetcode.cn/problems/design-an-ordered-stream)

[English Version](/solution/1600-1699/1656.Design%20an%20Ordered%20Stream/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有 <code>n</code> 个 <code>(id, value)</code> 对，其中 <code>id</code> 是 <code>1</code> 到 <code>n</code> 之间的一个整数，<code>value</code> 是一个字符串。不存在 <code>id</code> 相同的两个 <code>(id, value)</code> 对。</p>

<p>设计一个流，以 <strong>任意</strong> 顺序获取 <code>n</code> 个 <code>(id, value)</code> 对，并在多次调用时 <strong>按 <code>id</code> 递增的顺序</strong> 返回一些值。</p>

<p>实现 <code>OrderedStream</code> 类：</p>

<ul>
	<li><code>OrderedStream(int n)</code> 构造一个能接收 <code>n</code> 个值的流，并将当前指针 <code>ptr</code> 设为 <code>1</code> 。</li>
	<li><code>String[] insert(int id, String value)</code> 向流中存储新的 <code>(id, value)</code> 对。存储后：
	<ul>
		<li>如果流存储有 <code>id = ptr</code> 的 <code>(id, value)</code> 对，则找出从 <code>id = ptr</code> 开始的 <strong>最长 id 连续递增序列</strong> ，并 <strong>按顺序</strong> 返回与这些 id 关联的值的列表。然后，将 <code>ptr</code> 更新为最后那个  <code>id + 1</code> 。</li>
		<li>
		<p>否则，返回一个空列表。</p>
		</li>
	</ul>
	</li>
</ul>

<p> </p>

<p><strong>示例：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1656.Design%20an%20Ordered%20Stream/images/q1.gif" style="width: 682px; height: 240px;" /></strong></p>

<pre>
<strong>输入</strong>
["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
[[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
<strong>输出</strong>
[null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]

<strong>解释</strong>
OrderedStream os= new OrderedStream(5);
os.insert(3, "ccccc"); // 插入 (3, "ccccc")，返回 []
os.insert(1, "aaaaa"); // 插入 (1, "aaaaa")，返回 ["aaaaa"]
os.insert(2, "bbbbb"); // 插入 (2, "bbbbb")，返回 ["bbbbb", "ccccc"]
os.insert(5, "eeeee"); // 插入 (5, "eeeee")，返回 []
os.insert(4, "ddddd"); // 插入 (4, "ddddd")，返回 ["ddddd", "eeeee"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 1000</code></li>
	<li><code>1 <= id <= n</code></li>
	<li><code>value.length == 5</code></li>
	<li><code>value</code> 仅由小写字母组成</li>
	<li>每次调用 <code>insert</code> 都会使用一个唯一的 <code>id</code></li>
	<li>恰好调用 <code>n</code> 次 <code>insert</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数组模拟

我们可以使用一个长度为 $n + 1$ 的数组 $\textit{data}$ 来模拟这个流，其中 $\textit{data}[i]$ 表示 $\textit{id} = i$ 的值。同时，我们使用一个指针 $\textit{ptr}$ 来表示当前的位置。初始时 $\textit{ptr} = 1$。

在插入一个新的 $(\textit{idKey}, \textit{value})$ 对时，我们将 $\textit{data}[\textit{idKey}]$ 更新为 $\textit{value}$。然后，我们从 $\textit{ptr}$ 开始，依次将 $\textit{data}[\textit{ptr}]$ 加入答案中，直到 $\textit{data}[\textit{ptr}]$ 为空。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数据流的长度。

<!-- tabs:start -->

#### Python3

```python
class OrderedStream:

    def __init__(self, n: int):
        self.ptr = 1
        self.data = [None] * (n + 1)

    def insert(self, idKey: int, value: str) -> List[str]:
        self.data[idKey] = value
        ans = []
        while self.ptr < len(self.data) and self.data[self.ptr]:
            ans.append(self.data[self.ptr])
            self.ptr += 1
        return ans


# Your OrderedStream object will be instantiated and called as such:
# obj = OrderedStream(n)
# param_1 = obj.insert(idKey,value)
```

#### Java

```java
class OrderedStream {
    private int ptr = 1;
    private String[] data;

    public OrderedStream(int n) {
        data = new String[n + 1];
    }

    public List<String> insert(int idKey, String value) {
        data[idKey] = value;
        List<String> ans = new ArrayList<>();
        while (ptr < data.length && data[ptr] != null) {
            ans.add(data[ptr++]);
        }
        return ans;
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream obj = new OrderedStream(n);
 * List<String> param_1 = obj.insert(idKey,value);
 */
```

#### C++

```cpp
class OrderedStream {
public:
    OrderedStream(int n) {
        ptr = 1;
        data = vector<string>(n + 1);
    }

    vector<string> insert(int idKey, string value) {
        data[idKey] = value;
        vector<string> ans;
        while (ptr < data.size() && !data[ptr].empty()) {
            ans.push_back(data[ptr++]);
        }
        return ans;
    }

private:
    int ptr;
    vector<string> data;
};

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream* obj = new OrderedStream(n);
 * vector<string> param_1 = obj->insert(idKey,value);
 */
```

#### Go

```go
type OrderedStream struct {
	ptr  int
	data []string
}

func Constructor(n int) OrderedStream {
	return OrderedStream{
		ptr:  1,
		data: make([]string, n+1),
	}
}

func (this *OrderedStream) Insert(idKey int, value string) []string {
	this.data[idKey] = value
	var ans []string
	for this.ptr < len(this.data) && this.data[this.ptr] != "" {
		ans = append(ans, this.data[this.ptr])
		this.ptr++
	}
	return ans
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Insert(idKey,value);
 */
```

#### TypeScript

```ts
class OrderedStream {
    private ptr: number;
    private data: string[];

    constructor(n: number) {
        this.ptr = 1;
        this.data = Array(n + 1);
    }

    insert(idKey: number, value: string): string[] {
        this.data[idKey] = value;
        const ans: string[] = [];
        while (this.data[this.ptr]) {
            ans.push(this.data[this.ptr++]);
        }
        return ans;
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * var obj = new OrderedStream(n)
 * var param_1 = obj.insert(idKey,value)
 */
```

#### Rust

```rust
struct OrderedStream {
    ptr: usize,
    data: Vec<Option<String>>,
}

impl OrderedStream {
    fn new(n: i32) -> Self {
        OrderedStream {
            ptr: 1,
            data: vec![None; (n + 1) as usize],
        }
    }

    fn insert(&mut self, id_key: i32, value: String) -> Vec<String> {
        self.data[id_key as usize] = Some(value);
        let mut ans = Vec::new();
        while self.ptr < self.data.len() && self.data[self.ptr].is_some() {
            ans.push(self.data[self.ptr].take().unwrap());
            self.ptr += 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
