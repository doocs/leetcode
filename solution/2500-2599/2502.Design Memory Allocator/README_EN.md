# [2502. Design Memory Allocator](https://leetcode.com/problems/design-memory-allocator)

[中文文档](/solution/2500-2599/2502.Design%20Memory%20Allocator/README.md)

## Description

<p>You are given an integer <code>n</code> representing the size of a <strong>0-indexed</strong> memory array. All memory units are initially free.</p>

<p>You have a memory allocator with the following functionalities:</p>

<ol>
	<li><strong>Allocate </strong>a block of <code>size</code> consecutive free memory units and assign it the id <code>mID</code>.</li>
	<li><strong>Free</strong> all memory units with the given id <code>mID</code>.</li>
</ol>

<p><strong>Note</strong> that:</p>

<ul>
	<li>Multiple blocks can be allocated to the same <code>mID</code>.</li>
	<li>You should free all the memory units with <code>mID</code>, even if they were allocated in different blocks.</li>
</ul>

<p>Implement the <code>Allocator</code> class:</p>

<ul>
	<li><code>Allocator(int n)</code> Initializes an <code>Allocator</code> object with a memory array of size <code>n</code>.</li>
	<li><code>int allocate(int size, int mID)</code> Find the <strong>leftmost</strong> block of <code>size</code> <strong>consecutive</strong> free memory units and allocate it with the id <code>mID</code>. Return the block&#39;s first index. If such a block does not exist, return <code>-1</code>.</li>
	<li><code>int free(int mID)</code> Free all memory units with the id <code>mID</code>. Return the number of memory units you have freed.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Allocator&quot;, &quot;allocate&quot;, &quot;allocate&quot;, &quot;allocate&quot;, &quot;free&quot;, &quot;allocate&quot;, &quot;allocate&quot;, &quot;allocate&quot;, &quot;free&quot;, &quot;allocate&quot;, &quot;free&quot;]
[[10], [1, 1], [1, 2], [1, 3], [2], [3, 4], [1, 1], [1, 1], [1], [10, 2], [7]]
<strong>Output</strong>
[null, 0, 1, 2, 1, 3, 1, 6, 3, -1, 0]

<strong>Explanation</strong>
Allocator loc = new Allocator(10); // Initialize a memory array of size 10. All memory units are initially free.
loc.allocate(1, 1); // The leftmost block&#39;s first index is 0. The memory array becomes [<strong>1</strong>,_,_,_,_,_,_,_,_,_]. We return 0.
loc.allocate(1, 2); // The leftmost block&#39;s first index is 1. The memory array becomes [1,<strong>2</strong>,_,_,_,_,_,_,_,_]. We return 1.
loc.allocate(1, 3); // The leftmost block&#39;s first index is 2. The memory array becomes [1,2,<strong>3</strong>,_,_,_,_,_,_,_]. We return 2.
loc.free(2); // Free all memory units with mID 2. The memory array becomes [1,_, 3,_,_,_,_,_,_,_]. We return 1 since there is only 1 unit with mID 2.
loc.allocate(3, 4); // The leftmost block&#39;s first index is 3. The memory array becomes [1,_,3,<strong>4</strong>,<strong>4</strong>,<strong>4</strong>,_,_,_,_]. We return 3.
loc.allocate(1, 1); // The leftmost block&#39;s first index is 1. The memory array becomes [1,<strong>1</strong>,3,4,4,4,_,_,_,_]. We return 1.
loc.allocate(1, 1); // The leftmost block&#39;s first index is 6. The memory array becomes [1,1,3,4,4,4,<strong>1</strong>,_,_,_]. We return 6.
loc.free(1); // Free all memory units with mID 1. The memory array becomes [_,_,3,4,4,4,_,_,_,_]. We return 3 since there are 3 units with mID 1.
loc.allocate(10, 2); // We can not find any free block with 10 consecutive free memory units, so we return -1.
loc.free(7); // Free all memory units with mID 7. The memory array remains the same since there is no memory unit with mID 7. We return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, size, mID &lt;= 1000</code></li>
	<li>At most <code>1000</code> calls will be made to <code>allocate</code> and <code>free</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
