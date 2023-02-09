# [1797. 设计一个验证系统](https://leetcode.cn/problems/design-authentication-manager)

[English Version](/solution/1700-1799/1797.Design%20Authentication%20Manager/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你需要设计一个包含验证码的验证系统。每一次验证中，用户会收到一个新的验证码，这个验证码在 <code>currentTime</code> 时刻之后 <code>timeToLive</code> 秒过期。如果验证码被更新了，那么它会在 <code>currentTime</code> （可能与之前的 <code>currentTime</code> 不同）时刻延长 <code>timeToLive</code> 秒。</p>

<p>请你实现 <code>AuthenticationManager</code> 类：</p>

<ul>
	<li><code>AuthenticationManager(int timeToLive)</code> 构造 <code>AuthenticationManager</code> 并设置 <code>timeToLive</code> 参数。</li>
	<li><code>generate(string tokenId, int currentTime)</code> 给定 <code>tokenId</code> ，在当前时间 <code>currentTime</code> 生成一个新的验证码。</li>
	<li><code>renew(string tokenId, int currentTime)</code> 将给定 <code>tokenId</code> 且 <strong>未过期</strong> 的验证码在 <code>currentTime</code> 时刻更新。如果给定 <code>tokenId</code> 对应的验证码不存在或已过期，请你忽略该操作，不会有任何更新操作发生。</li>
	<li><code>countUnexpiredTokens(int currentTime)</code> 请返回在给定 <code>currentTime</code> 时刻，<strong>未过期</strong> 的验证码数目。</li>
</ul>

<p>如果一个验证码在时刻 <code>t</code> 过期，且另一个操作恰好在时刻 <code>t</code> 发生（<code>renew</code> 或者 <code>countUnexpiredTokens</code> 操作），过期事件 <strong>优先于</strong> 其他操作。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1797.Design%20Authentication%20Manager/images/copy-of-pc68_q2.png" style="width: 500px; height: 287px;" />
<pre>
<strong>输入：</strong>
["AuthenticationManager", "<code>renew</code>", "generate", "<code>countUnexpiredTokens</code>", "generate", "<code>renew</code>", "<code>renew</code>", "<code>countUnexpiredTokens</code>"]
[[5], ["aaa", 1], ["aaa", 2], [6], ["bbb", 7], ["aaa", 8], ["bbb", 10], [15]]
<strong>输出：</strong>
[null, null, null, 1, null, null, null, 0]

<strong>解释：</strong>
AuthenticationManager authenticationManager = new AuthenticationManager(5); // 构造 AuthenticationManager ，设置 <code>timeToLive</code> = 5 秒。
authenticationManager.<code>renew</code>("aaa", 1); // 时刻 1 时，没有验证码的 tokenId 为 "aaa" ，没有验证码被更新。
authenticationManager.generate("aaa", 2); // 时刻 2 时，生成一个 tokenId 为 "aaa" 的新验证码。
authenticationManager.<code>countUnexpiredTokens</code>(6); // 时刻 6 时，只有 tokenId 为 "aaa" 的验证码未过期，所以返回 1 。
authenticationManager.generate("bbb", 7); // 时刻 7 时，生成一个 tokenId 为 "bbb" 的新验证码。
authenticationManager.<code>renew</code>("aaa", 8); // tokenId 为 "aaa" 的验证码在时刻 7 过期，且 8 >= 7 ，所以时刻 8 的 renew 操作被忽略，没有验证码被更新。
authenticationManager.<code>renew</code>("bbb", 10); // tokenId 为 "bbb" 的验证码在时刻 10 没有过期，所以 renew 操作会执行，该 token 将在时刻 15 过期。
authenticationManager.<code>countUnexpiredTokens</code>(15); // tokenId 为 "bbb" 的验证码在时刻 15 过期，tokenId 为 "aaa" 的验证码在时刻 7 过期，所有验证码均已过期，所以返回 0 。

</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= timeToLive <= 10<sup>8</sup></code></li>
	<li><code>1 <= currentTime <= 10<sup>8</sup></code></li>
	<li><code>1 <= tokenId.length <= 5</code></li>
	<li><code>tokenId</code> 只包含小写英文字母。</li>
	<li>所有 <code>generate</code> 函数的调用都会包含独一无二的 <code>tokenId</code> 值。</li>
	<li>所有函数调用中，<code>currentTime</code> 的值 <strong>严格递增</strong> 。</li>
	<li>所有函数的调用次数总共不超过 <code>2000</code> 次。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们可以简单维护一个哈希表 $d$，键为 `tokenId`，值为过期时间。

-   `generate` 操作时，将 `tokenId` 作为键，`currentTime + timeToLive` 作为值存入哈希表 $d$ 中。
-   `renew` 操作时，如果 `tokenId` 不在哈希表 $d$ 中，或者 `currentTime >= d[tokenId]`，则忽略该操作；否则，更新 `d[tokenId]` 为 `currentTime + timeToLive`。
-   `countUnexpiredTokens` 操作时，遍历哈希表 $d$，统计未过期的 `tokenId` 个数。

时间复杂度方面，`generate` 和 `renew` 操作的时间复杂度均为 $O(1)$，`countUnexpiredTokens` 操作的时间复杂度为 $O(n)$，其中 $n$ 为哈希表 $d$ 的键值对个数。

空间复杂度为 $O(n)$，其中 $n$ 为哈希表 $d$ 的键值对个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class AuthenticationManager:

    def __init__(self, timeToLive: int):
        self.t = timeToLive
        self.d = defaultdict(int)

    def generate(self, tokenId: str, currentTime: int) -> None:
        self.d[tokenId] = currentTime + self.t

    def renew(self, tokenId: str, currentTime: int) -> None:
        if self.d[tokenId] <= currentTime:
            return
        self.d[tokenId] = currentTime + self.t

    def countUnexpiredTokens(self, currentTime: int) -> int:
        return sum(exp > currentTime for exp in self.d.values())


# Your AuthenticationManager object will be instantiated and called as such:
# obj = AuthenticationManager(timeToLive)
# obj.generate(tokenId,currentTime)
# obj.renew(tokenId,currentTime)
# param_3 = obj.countUnexpiredTokens(currentTime)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class AuthenticationManager {
    private int t;
    private Map<String, Integer> d = new HashMap<>();

    public AuthenticationManager(int timeToLive) {
        t = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        d.put(tokenId, currentTime + t);
    }

    public void renew(String tokenId, int currentTime) {
        if (d.getOrDefault(tokenId, 0) <= currentTime) {
            return;
        }
        generate(tokenId, currentTime);
    }

    public int countUnexpiredTokens(int currentTime) {
        int ans = 0;
        for (int exp : d.values()) {
            if (exp > currentTime) {
                ++ans;
            }
        }
        return ans;
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */
```

### **C++**

```cpp
class AuthenticationManager {
public:
    AuthenticationManager(int timeToLive) {
        t = timeToLive;
    }

    void generate(string tokenId, int currentTime) {
        d[tokenId] = currentTime + t;
    }

    void renew(string tokenId, int currentTime) {
        if (d[tokenId] <= currentTime) return;
        generate(tokenId, currentTime);
    }

    int countUnexpiredTokens(int currentTime) {
        int ans = 0;
        for (auto& [_, v] : d) ans += v > currentTime;
        return ans;
    }

private:
    int t;
    unordered_map<string, int> d;
};

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager* obj = new AuthenticationManager(timeToLive);
 * obj->generate(tokenId,currentTime);
 * obj->renew(tokenId,currentTime);
 * int param_3 = obj->countUnexpiredTokens(currentTime);
 */
```

### **Go**

```go
type AuthenticationManager struct {
	t int
	d map[string]int
}

func Constructor(timeToLive int) AuthenticationManager {
	return AuthenticationManager{timeToLive, map[string]int{}}
}

func (this *AuthenticationManager) Generate(tokenId string, currentTime int) {
	this.d[tokenId] = currentTime + this.t
}

func (this *AuthenticationManager) Renew(tokenId string, currentTime int) {
	if v, ok := this.d[tokenId]; !ok || v <= currentTime {
		return
	}
	this.Generate(tokenId, currentTime)
}

func (this *AuthenticationManager) CountUnexpiredTokens(currentTime int) int {
	ans := 0
	for _, exp := range this.d {
		if exp > currentTime {
			ans++
		}
	}
	return ans
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * obj := Constructor(timeToLive);
 * obj.Generate(tokenId,currentTime);
 * obj.Renew(tokenId,currentTime);
 * param_3 := obj.CountUnexpiredTokens(currentTime);
 */
```

### **TypeScript**

```ts
class AuthenticationManager {
    private timeToLive: number;
    private map: Map<string, number>;

    constructor(timeToLive: number) {
        this.timeToLive = timeToLive;
        this.map = new Map<string, number>();
    }

    generate(tokenId: string, currentTime: number): void {
        this.map.set(tokenId, currentTime + this.timeToLive);
    }

    renew(tokenId: string, currentTime: number): void {
        if ((this.map.get(tokenId) ?? 0) <= currentTime) {
            return;
        }
        this.map.set(tokenId, currentTime + this.timeToLive);
    }

    countUnexpiredTokens(currentTime: number): number {
        let res = 0;
        for (const time of this.map.values()) {
            if (time > currentTime) {
                res++;
            }
        }
        return res;
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * var obj = new AuthenticationManager(timeToLive)
 * obj.generate(tokenId,currentTime)
 * obj.renew(tokenId,currentTime)
 * var param_3 = obj.countUnexpiredTokens(currentTime)
 */
```

### **Rust**

```rust
use std::collections::HashMap;
struct AuthenticationManager {
    time_to_live: i32,
    map: HashMap<String, i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl AuthenticationManager {
    fn new(timeToLive: i32) -> Self {
        Self {
            time_to_live: timeToLive,
            map: HashMap::new(),
        }
    }

    fn generate(&mut self, token_id: String, current_time: i32) {
        self.map.insert(token_id, current_time + self.time_to_live);
    }

    fn renew(&mut self, token_id: String, current_time: i32) {
        if self.map.get(&token_id).unwrap_or(&0) <= &current_time {
            return;
        }
        self.map.insert(token_id, current_time + self.time_to_live);
    }

    fn count_unexpired_tokens(&self, current_time: i32) -> i32 {
        self.map.values().filter(|&time| *time > current_time).count() as i32
    }
}


/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * let obj = AuthenticationManager::new(timeToLive);
 * obj.generate(tokenId, currentTime);
 * obj.renew(tokenId, currentTime);
 * let ret_3: i32 = obj.count_unexpired_tokens(currentTime);
 */
```

### **...**

```

```

<!-- tabs:end -->
