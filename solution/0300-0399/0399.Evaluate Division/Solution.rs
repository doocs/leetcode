use std::collections::HashMap;

#[derive(Debug)]
pub struct DSUNode {
    parent: String,
    weight: f64,
}

pub struct DisjointSetUnion {
    nodes: HashMap<String, DSUNode>,
}

impl DisjointSetUnion {
    pub fn new(equations: &Vec<Vec<String>>) -> DisjointSetUnion {
        let mut nodes = HashMap::new();
        for equation in equations.iter() {
            for iter in equation.iter() {
                nodes.insert(
                    iter.clone(),
                    DSUNode {
                        parent: iter.clone(),
                        weight: 1.0,
                    },
                );
            }
        }
        DisjointSetUnion { nodes }
    }

    pub fn find(&mut self, v: &String) -> String {
        let origin = self.nodes[v].parent.clone();
        if origin == *v {
            return origin;
        }

        let root = self.find(&origin);
        self.nodes.get_mut(v).unwrap().parent = root.clone();
        self.nodes.get_mut(v).unwrap().weight *= self.nodes[&origin].weight;
        root
    }

    pub fn union(&mut self, a: &String, b: &String, v: f64) {
        let pa = self.find(a);
        let pb = self.find(b);
        if pa == pb {
            return;
        }
        let (wa, wb) = (self.nodes[a].weight, self.nodes[b].weight);
        self.nodes.get_mut(&pa).unwrap().parent = pb;
        self.nodes.get_mut(&pa).unwrap().weight = (wb * v) / wa;
    }

    pub fn exist(&mut self, k: &String) -> bool {
        self.nodes.contains_key(k)
    }

    pub fn calc_value(&mut self, a: &String, b: &String) -> f64 {
        if !self.exist(a) || !self.exist(b) || self.find(a) != self.find(b) {
            -1.0
        } else {
            let wa = self.nodes[a].weight;
            let wb = self.nodes[b].weight;
            wa / wb
        }
    }
}

impl Solution {
    pub fn calc_equation(
        equations: Vec<Vec<String>>,
        values: Vec<f64>,
        queries: Vec<Vec<String>>,
    ) -> Vec<f64> {
        let mut dsu = DisjointSetUnion::new(&equations);
        for (i, &v) in values.iter().enumerate() {
            let (a, b) = (&equations[i][0], &equations[i][1]);
            dsu.union(a, b, v);
        }

        let mut ans = vec![];
        for querie in queries {
            let (c, d) = (&querie[0], &querie[1]);
            ans.push(dsu.calc_value(c, d));
        }
        ans
    }
}
