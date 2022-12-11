# [2502. 设计内存分配器](https://leetcode.cn/problems/design-memory-allocator)

[English Version](/solution/2500-2599/2502.Design%20Memory%20Allocator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，表示下标从 <strong>0</strong> 开始的内存数组的大小。所有内存单元开始都是空闲的。</p>

<p>请你设计一个具备以下功能的内存分配器：</p>

<ol>
	<li><strong>分配 </strong>一块大小为 <code>size</code> 的连续空闲内存单元并赋 id <code>mID</code> 。</li>
	<li><strong>释放</strong> 给定 id <code>mID</code> 对应的所有内存单元。</li>
</ol>

<p><strong>注意：</strong></p>

<ul>
	<li>多个块可以被分配到同一个 <code>mID</code> 。</li>
	<li>你必须释放 <code>mID</code> 对应的所有内存单元，即便这些内存单元被分配在不同的块中。</li>
</ul>

<p>实现 <code>Allocator</code> 类：</p>

<ul>
	<li><code>Allocator(int n)</code> 使用一个大小为 <code>n</code> 的内存数组初始化 <code>Allocator</code> 对象。</li>
	<li><code>int allocate(int size, int mID)</code> 找出大小为 <code>size</code> 个连续空闲内存单元且位于&nbsp; <strong>最左侧</strong> 的块，分配并赋 id <code>mID</code> 。返回块的第一个下标。如果不存在这样的块，返回 <code>-1</code> 。</li>
	<li><code>int free(int mID)</code> 释放 id <code>mID</code> 对应的所有内存单元。返回释放的内存单元数目。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入</strong>
["Allocator", "allocate", "allocate", "allocate", "free", "allocate", "allocate", "allocate", "free", "allocate", "free"]
[[10], [1, 1], [1, 2], [1, 3], [2], [3, 4], [1, 1], [1, 1], [1], [10, 2], [7]]
<strong>输出</strong>
[null, 0, 1, 2, 1, 3, 1, 6, 3, -1, 0]

<strong>解释</strong>
Allocator loc = new Allocator(10); // 初始化一个大小为 10 的内存数组，所有内存单元都是空闲的。
loc.allocate(1, 1); // 最左侧的块的第一个下标是 0 。内存数组变为 [<strong>1</strong>, , , , , , , , , ]。返回 0 。
loc.allocate(1, 2); // 最左侧的块的第一个下标是 1 。内存数组变为 [1,<strong>2</strong>, , , , , , , , ]。返回 1 。
loc.allocate(1, 3); // 最左侧的块的第一个下标是 2 。内存数组变为 [1,2,<strong>3</strong>, , , , , , , ]。返回 2 。
loc.free(2); // 释放 mID 为 2 的所有内存单元。内存数组变为 [1, ,<strong>3</strong>, , , , , , , ] 。返回 1 ，因为只有 1 个 mID 为 2 的内存单元。
loc.allocate(3, 4); // 最左侧的块的第一个下标是 3 。内存数组变为 [1, ,3,<strong>4</strong>,<strong>4</strong>,<strong>4</strong>, , , , ]。返回 3 。
loc.allocate(1, 1); // 最左侧的块的第一个下标是 1 。内存数组变为 [1,<strong>1</strong>,3,4,4,4, , , , ]。返回 1 。
loc.allocate(1, 1); // 最左侧的块的第一个下标是 6 。内存数组变为 [1,1,3,4,4,4,<strong>1</strong>, , , ]。返回 6 。
loc.free(1); // 释放 mID 为 1 的所有内存单元。内存数组变为 [ , ,3,4,4,4,<strong> </strong>, , , ] 。返回 3 ，因为有 3 个 mID 为 1 的内存单元。
loc.allocate(10, 2); // 无法找出长度为 10 个连续空闲内存单元的空闲块，所有返回 -1 。
loc.free(7); // 释放 mID 为 7 的所有内存单元。内存数组保持原状，因为不存在 mID 为 7 的内存单元。返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, size, mID &lt;= 1000</code></li>
	<li>最多调用 <code>allocate</code> 和 <code>free</code> 方法 <code>1000</code> 次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力模拟**

题目数据范围不大，可以直接用数组模拟内存空间。

初始化时，将数组中的每个元素置为 0，表示空闲。

当调用 `allocate` 方法时，遍历数组，找到连续的 `size` 个空闲内存单元，将其置为 `mID`，并返回第一个下标。

当调用 `free` 方法时，遍历数组，将所有等于 `mID` 的内存单元置为 $0$，表示空闲。

时间复杂度 $O(n \times q)$，空间复杂度 $O(n)$，其中 $n$ 和 $q$ 分别为内存空间的大小和方法调用的次数。

**方法二：哈希表 + 有序集合**

我们可以用有序集合维护所有已分配的内存单元的起始下标和结束下标，其中起始下标为键，结束下标为值；另外用哈希表维护 `mID` 和其对应的内存单元的起始下标。

当调用 `allocate` 方法时，遍历有序集合，找到第一个长度大于等于 `size` 的空闲区间，将其分配给 `mID`，并更新有序集合。然后将 `mID` 和其对应的内存单元的起始下标加入哈希表。

当调用 `free` 方法时，从哈希表中找到 `mID` 对应的内存单元的起始下标，然后将其从有序集合中删除，再将 `mID` 从哈希表中删除。

时间复杂度 $O(q \log n)$，空间复杂度 $O(n)$，其中 $n$ 和 $q$ 分别为内存空间的大小和方法调用的次数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Allocator:

    def __init__(self, n: int):
        self.m = [0] * n

    def allocate(self, size: int, mID: int) -> int:
        cnt = 0
        for i, v in enumerate(self.m):
            if v:
                cnt = 0
            else:
                cnt += 1
                if cnt == size:
                    self.m[i - size + 1: i + 1] = [mID] * size
                    return i - size + 1
        return -1


    def free(self, mID: int) -> int:
        ans = 0
        for i, v in enumerate(self.m):
            if v == mID:
                self.m[i] = 0
                ans += 1
        return ans

# Your Allocator object will be instantiated and called as such:
# obj = Allocator(n)
# param_1 = obj.allocate(size,mID)
# param_2 = obj.free(mID)
```

```python
from sortedcontainers import SortedList


class Allocator:

    def __init__(self, n: int):
        self.sl = SortedList([(-1, -1), (n, n)])
        self.d = defaultdict(list)

    def allocate(self, size: int, mID: int) -> int:
        for (_, s), (e, _) in pairwise(self.sl):
            s, e = s + 1, e - 1
            if e - s + 1 >= size:
                self.sl.add((s, s + size - 1))
                self.d[mID].append((s, s + size - 1))
                return s
        return -1

    def free(self, mID: int) -> int:
        ans = 0
        for block in self.d[mID]:
            self.sl.remove(block)
            ans += block[1] - block[0] + 1
        del self.d[mID]
        return ans


# Your Allocator object will be instantiated and called as such:
# obj = Allocator(n)
# param_1 = obj.allocate(size,mID)
# param_2 = obj.free(mID)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Allocator {
    private int[] m;

    public Allocator(int n) {
        m = new int[n];
    }

    public int allocate(int size, int mID) {
        int cnt = 0;
        for (int i = 0; i < m.length; ++i) {
            if (m[i] > 0) {
                cnt = 0;
            } else if (++cnt == size) {
                Arrays.fill(m, i - size + 1, i + 1, mID);
                return i - size + 1;
            }
        }
        return -1;
    }

    public int free(int mID) {
        int ans = 0;
        for (int i = 0; i < m.length; ++i) {
            if (m[i] == mID) {
                m[i] = 0;
                ++ans;
            }
        }
        return ans;
    }
}

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator obj = new Allocator(n);
 * int param_1 = obj.allocate(size,mID);
 * int param_2 = obj.free(mID);
 */
```

```java
class Allocator {
    private TreeMap<Integer, Integer> tm = new TreeMap<>();
    private Map<Integer, List<Integer>> d = new HashMap<>();

    public Allocator(int n) {
        tm.put(-1, -1);
        tm.put(n, n);
    }

    public int allocate(int size, int mID) {
        int s = -1;
        for (var entry : tm.entrySet()) {
            int v = entry.getKey();
            if (s != -1) {
                int e = v - 1;
                if (e - s + 1 >= size) {
                    tm.put(s, s + size - 1);
                    d.computeIfAbsent(mID, k -> new ArrayList<>()).add(s);
                    return s;
                }
            }
            s = entry.getValue() + 1;
        }
        return -1;
    }

    public int free(int mID) {
        int ans = 0;
        for (int s : d.getOrDefault(mID, Collections.emptyList())) {
            int e = tm.remove(s);
            ans += e - s + 1;
        }
        d.remove(mID);
        return ans;
    }
}

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator obj = new Allocator(n);
 * int param_1 = obj.allocate(size,mID);
 * int param_2 = obj.free(mID);
 */
```

### **C++**

```cpp
class Allocator {
public:
    Allocator(int n) {
        m = vector<int>(n);
    }

    int allocate(int size, int mID) {
        int cnt = 0;
        for (int i = 0; i < m.size(); ++i) {
            if (m[i]) {
                cnt = 0;
            } else if (++cnt == size) {
                fill(i - size + 1, i + 1, mID);
                return i - size + 1;
            }
        }
        return -1;
    }

    int free(int mID) {
        int ans = 0;
        for (int i = 0; i < m.size(); ++i) {
            if (m[i] == mID) {
                m[i] = 0;
                ++ans;
            }
        }
        return ans;
    }

private:
    vector<int> m;

    void fill(int from, int to, int val) {
        for (int i = from; i < to; ++i) {
            m[i] = val;
        }
    }
};

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator* obj = new Allocator(n);
 * int param_1 = obj->allocate(size,mID);
 * int param_2 = obj->free(mID);
 */
```

```cpp
class Allocator {
public:
    Allocator(int n) {
        tm[-1] = -1;
        tm[n] = n;
    }

    int allocate(int size, int mID) {
        int s = -1;
        for (auto& [v, c] : tm) {
            if (s != -1) {
                int e = v - 1;
                if (e - s + 1 >= size) {
                    tm[s] = s + size - 1;
                    d[mID].emplace_back(s);
                    return s;
                }
            }
            s = c + 1;
        }
        return -1;
    }

    int free(int mID) {
        int ans = 0;
        for (int& s : d[mID]) {
            int e = tm[s];
            tm.erase(s);
            ans += e - s + 1;
        }
        d.erase(mID);
        return ans;
    }

private:
    map<int, int> tm;
    unordered_map<int, vector<int>> d;
};

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator* obj = new Allocator(n);
 * int param_1 = obj->allocate(size,mID);
 * int param_2 = obj->free(mID);
 */
```

### **Go**

```go
type Allocator struct {
	m []int
}

func Constructor(n int) Allocator {
	return Allocator{make([]int, n)}
}

func (this *Allocator) Allocate(size int, mID int) int {
	cnt := 0
	for i, v := range this.m {
		if v > 0 {
			cnt = 0
		} else {
			cnt++
			if cnt == size {
				for j := i - size + 1; j <= i; j++ {
					this.m[j] = mID
				}
				return i - size + 1
			}
		}
	}
	return -1
}

func (this *Allocator) Free(mID int) (ans int) {
	for i, v := range this.m {
		if v == mID {
			this.m[i] = 0
			ans++
		}
	}
	return
}

/**
 * Your Allocator object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Allocate(size,mID);
 * param_2 := obj.Free(mID);
 */
```

```go
type Allocator struct {
	rbt *redblacktree.Tree
	d   map[int][]int
}

func Constructor(n int) Allocator {
	rbt := redblacktree.NewWithIntComparator()
	rbt.Put(-1, -1)
	rbt.Put(n, n)
	return Allocator{rbt, map[int][]int{}}
}

func (this *Allocator) Allocate(size int, mID int) int {
	s := -1
	it := this.rbt.Iterator()
	for it.Next() {
		v := it.Key().(int)
		if s != -1 {
			e := v - 1
			if e-s+1 >= size {
				this.rbt.Put(s, s+size-1)
				this.d[mID] = append(this.d[mID], s)
				return s
			}
		}
		s = it.Value().(int) + 1
	}
	return -1
}

func (this *Allocator) Free(mID int) int {
	ans := 0
	for _, s := range this.d[mID] {
		if e, ok := this.rbt.Get(s); ok {
			this.rbt.Remove(s)
			ans += e.(int) - s + 1
		}
	}
	this.d[mID] = []int{}
	return ans
}

/**
 * Your Allocator object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Allocate(size,mID);
 * param_2 := obj.Free(mID);
 */
```

### **...**

```

```

<!-- tabs:end -->
