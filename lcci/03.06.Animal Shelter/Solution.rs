use std::collections::VecDeque;

struct AnimalShelf {
    cats: VecDeque<i32>,
    dogs: VecDeque<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl AnimalShelf {
    fn new() -> Self {
        Self {
            cats: VecDeque::new(),
            dogs: VecDeque::new(),
        }
    }

    fn enqueue(&mut self, animal: Vec<i32>) {
        if animal[1] == 0 {
            self.cats.push_back(animal[0]);
        } else {
            self.dogs.push_back(animal[0]);
        }
    }

    fn dequeue_any(&mut self) -> Vec<i32> {
        match (self.cats.is_empty(), self.dogs.is_empty()) {
            (true, true) => vec![-1, -1],
            (true, false) => self.dequeue_dog(),
            (false, true) => self.dequeue_cat(),
            (false, false) => {
                if self.dogs[0] < self.cats[0] {
                    self.dequeue_dog()
                } else {
                    self.dequeue_cat()
                }
            }
        }
    }

    fn dequeue_dog(&mut self) -> Vec<i32> {
        if self.dogs.is_empty() {
            return vec![-1, -1];
        }
        vec![self.dogs.pop_front().unwrap(), 1]
    }

    fn dequeue_cat(&mut self) -> Vec<i32> {
        if self.cats.is_empty() {
            return vec![-1, -1];
        }
        vec![self.cats.pop_front().unwrap(), 0]
    }
}

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * let obj = AnimalShelf::new();
 * obj.enqueue(animal);
 * let ret_2: Vec<i32> = obj.dequeue_any();
 * let ret_3: Vec<i32> = obj.dequeue_dog();
 * let ret_4: Vec<i32> = obj.dequeue_cat();
 */