use std::collections::{HashSet, HashMap, VecDeque};

struct Router {
    lim: usize,
    vis: HashSet<i64>,
    q: VecDeque<(i32, i32, i32)>,
    idx: HashMap<i32, usize>,
    d: HashMap<i32, Vec<i32>>,
}

impl Router {
    fn new(memory_limit: i32) -> Self {
        Router {
            lim: memory_limit as usize,
            vis: HashSet::new(),
            q: VecDeque::new(),
            idx: HashMap::new(),
            d: HashMap::new(),
        }
    }

    fn f(a: i32, b: i32, c: i32) -> i64 {
        ((a as i64) << 46) | ((b as i64) << 29) | (c as i64)
    }

    fn add_packet(&mut self, source: i32, destination: i32, timestamp: i32) -> bool {
        let x = Self::f(source, destination, timestamp);
        if self.vis.contains(&x) {
            return false;
        }
        self.vis.insert(x);
        if self.q.len() >= self.lim {
            self.forward_packet();
        }
        self.q.push_back((source, destination, timestamp));
        self.d.entry(destination).or_default().push(timestamp);
        true
    }

    fn forward_packet(&mut self) -> Vec<i32> {
        if let Some((s, d, t)) = self.q.pop_front() {
            self.vis.remove(&Self::f(s, d, t));
            *self.idx.entry(d).or_insert(0) += 1;
            vec![s, d, t]
        } else {
            vec![]
        }
    }

    fn get_count(&self, destination: i32, start_time: i32, end_time: i32) -> i32 {
        let ls = self.d.get(&destination);
        if ls.is_none() {
            return 0;
        }
        let ls = ls.unwrap();
        let k = *self.idx.get(&destination).unwrap_or(&0);
        let i = k + ls[k..].partition_point(|&x| x < start_time);
        let j = k + ls[k..].partition_point(|&x| x < end_time + 1);
        (j - i) as i32
    }
}
