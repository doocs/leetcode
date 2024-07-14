use std::collections::VecDeque;

struct AnimalShelf {
    q: [VecDeque<i32>; 2],
}

impl AnimalShelf {
    fn new() -> Self {
        AnimalShelf {
            q: [VecDeque::new(), VecDeque::new()],
        }
    }

    fn enqueue(&mut self, animal: Vec<i32>) {
        self.q[animal[1] as usize].push_back(animal[0]);
    }

    fn dequeue_any(&mut self) -> Vec<i32> {
        if self.q[0].is_empty()
            || (!self.q[1].is_empty() && self.q[1].front().unwrap() < self.q[0].front().unwrap())
        {
            self.dequeue_dog()
        } else {
            self.dequeue_cat()
        }
    }

    fn dequeue_dog(&mut self) -> Vec<i32> {
        if self.q[1].is_empty() {
            vec![-1, -1]
        } else {
            let dog = self.q[1].pop_front().unwrap();
            vec![dog, 1]
        }
    }

    fn dequeue_cat(&mut self) -> Vec<i32> {
        if self.q[0].is_empty() {
            vec![-1, -1]
        } else {
            let cat = self.q[0].pop_front().unwrap();
            vec![cat, 0]
        }
    }
}
