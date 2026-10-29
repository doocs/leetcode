impl Solution {
    pub fn palindrome_path(
        n: i32,
        edges: Vec<Vec<i32>>,
        s: String,
        queries: Vec<String>,
    ) -> Vec<bool> {
        let n = n as usize;
        let mut g = vec![vec![]; n];
        for e in &edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            g[u].push(v);
            g[v].push(u);
        }
        let mut parent = vec![0usize; n];
        let mut depth = vec![0usize; n];
        let mut tin = vec![0usize; n];
        let mut tout = vec![0usize; n];
        let mut timer = 0usize;
        let mut stack = vec![(0usize, 0usize, 0usize)];
        while let Some((u, p, state)) = stack.pop() {
            if state == 0 {
                parent[u] = p;
                depth[u] = if u == p { 0 } else { depth[p] + 1 };
                tin[u] = timer;
                timer += 1;
                stack.push((u, p, 1));
                for &v in &g[u] {
                    if v != p {
                        stack.push((v, u, 0));
                    }
                }
            } else {
                tout[u] = timer - 1;
            }
        }
        let log = (usize::BITS as usize) - n.leading_zeros() as usize + 1;
        let mut up = vec![vec![0usize; n]; log];
        up[0] = parent.clone();
        up[0][0] = 0;
        for k in 1..log {
            for v in 0..n {
                up[k][v] = up[k - 1][up[k - 1][v]];
            }
        }
        let lca = |u: usize, v: usize, depth: &[usize], up: &[Vec<usize>]| -> usize {
            let (mut u, mut v) = if depth[u] >= depth[v] { (u, v) } else { (v, u) };
            let diff = depth[u] - depth[v];
            for k in 0..log {
                if (diff >> k) & 1 == 1 {
                    u = up[k][u];
                }
            }
            if u == v {
                return u;
            }
            for k in (0..log).rev() {
                if up[k][u] != up[k][v] {
                    u = up[k][u];
                    v = up[k][v];
                }
            }
            up[0][u]
        };
        let mut bit = vec![0u32; n + 2];
        let bit_upd = |bit: &mut [u32], mut i: usize, delta: u32| {
            i += 1;
            while i < bit.len() {
                bit[i] ^= delta;
                i += i & i.wrapping_neg();
            }
        };
        let bit_pref = |bit: &[u32], mut i: usize| -> u32 {
            i += 1;
            let mut r = 0u32;
            while i > 0 {
                r ^= bit[i];
                i -= i & i.wrapping_neg();
            }
            r
        };
        let mut chars: Vec<u8> = s.into_bytes();
        let bit_of = |c: u8| -> u32 { 1u32 << (c - b'a') };
        let apply = |bit: &mut [u32], tin: &[usize], tout: &[usize], u: usize, delta: u32| {
            bit_upd(bit, tin[u], delta);
            if tout[u] + 1 < n {
                bit_upd(bit, tout[u] + 1, delta);
            }
        };
        for u in 0..n {
            apply(&mut bit, &tin, &tout, u, bit_of(chars[u]));
        }
        let mut ans = Vec::new();
        for q in queries {
            let parts: Vec<&str> = q.split_whitespace().collect();
            if parts[0] == "update" {
                let u: usize = parts[1].parse().unwrap();
                let c = parts[2].as_bytes()[0];
                let delta = bit_of(chars[u]) ^ bit_of(c);
                if delta != 0 {
                    apply(&mut bit, &tin, &tout, u, delta);
                    chars[u] = c;
                }
            } else {
                let u: usize = parts[1].parse().unwrap();
                let v: usize = parts[2].parse().unwrap();
                let w = lca(u, v, &depth, &up);
                let mask = bit_pref(&bit, tin[u]) ^ bit_pref(&bit, tin[v]) ^ bit_of(chars[w]);
                ans.push(mask & mask.wrapping_sub(1) == 0);
            }
        }
        ans
    }
}
