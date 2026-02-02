use std::cmp::min;

struct Node {
    children: [Option<Box<Node>>; 26],
    v: i32,
}

impl Node {
    fn new() -> Self {
        Self {
            children: std::array::from_fn(|_| None),
            v: -1,
        }
    }
}

impl Solution {
    pub fn minimum_cost(
        source: String,
        target: String,
        original: Vec<String>,
        changed: Vec<String>,
        cost: Vec<i32>,
    ) -> i64 {
        let inf: i64 = 1 << 60;
        let mut root = Box::new(Node::new());
        let mut idx: usize = 0;
        let m = cost.len();
        let n = m << 1;

        let mut g = vec![vec![inf; n]; n];
        for i in 0..n {
            g[i][i] = 0;
        }

        let mut insert = |w: &str, root: &mut Box<Node>, idx: &mut usize| -> usize {
            let mut node: &mut Box<Node> = root;
            for c in w.bytes() {
                let i = (c - b'a') as usize;
                if node.children[i].is_none() {
                    node.children[i] = Some(Box::new(Node::new()));
                }
                node = node.children[i].as_mut().unwrap();
            }
            if node.v < 0 {
                node.v = *idx as i32;
                *idx += 1;
            }
            node.v as usize
        };

        for i in 0..m {
            let x = insert(&original[i], &mut root, &mut idx);
            let y = insert(&changed[i], &mut root, &mut idx);
            g[x][y] = min(g[x][y], cost[i] as i64);
        }

        for k in 0..idx {
            for i in 0..idx {
                if g[i][k] >= inf {
                    continue;
                }
                for j in 0..idx {
                    let v = g[i][k] + g[k][j];
                    if v < g[i][j] {
                        g[i][j] = v;
                    }
                }
            }
        }

        let s = source.into_bytes();
        let t = target.into_bytes();
        let len = s.len();
        let mut f: Vec<Option<i64>> = vec![None; len];

        fn dfs(
            i: usize,
            s: &[u8],
            t: &[u8],
            root: &Box<Node>,
            g: &Vec<Vec<i64>>,
            f: &mut Vec<Option<i64>>,
            inf: i64,
        ) -> i64 {
            if i >= s.len() {
                return 0;
            }
            if let Some(v) = f[i] {
                return v;
            }
            let mut res = if s[i] == t[i] {
                dfs(i + 1, s, t, root, g, f, inf)
            } else {
                inf
            };
            let mut p: Option<&Box<Node>> = Some(root);
            let mut q: Option<&Box<Node>> = Some(root);
            for j in i..s.len() {
                p = p.and_then(|x| x.children[(s[j] - b'a') as usize].as_ref());
                q = q.and_then(|x| x.children[(t[j] - b'a') as usize].as_ref());
                if p.is_none() || q.is_none() {
                    break;
                }
                let pv = p.unwrap().v;
                let qv = q.unwrap().v;
                if pv < 0 || qv < 0 {
                    continue;
                }
                let c = g[pv as usize][qv as usize];
                if c < inf {
                    let v = c + dfs(j + 1, s, t, root, g, f, inf);
                    if v < res {
                        res = v;
                    }
                }
            }
            f[i] = Some(res);
            res
        }

        let ans = dfs(0, &s, &t, &root, &g, &mut f, inf);
        if ans >= inf { -1 } else { ans }
    }
}
