use std::collections::{ HashMap, HashSet };

#[derive(Clone)]
struct Task {
    task_id: i32,
    description: String,
    tags: HashSet<String>,
    due_date: i32,
}

struct TodoList {
    /// The global task id
    id: i32,
    /// The mapping from `user_id` to `task`
    user_map: HashMap<i32, Vec<Task>>,
}

impl TodoList {
    fn new() -> Self {
        Self {
            id: 1,
            user_map: HashMap::new(),
        }
    }

    fn add_task(
        &mut self,
        user_id: i32,
        task_description: String,
        due_date: i32,
        tags: Vec<String>
    ) -> i32 {
        if self.user_map.contains_key(&user_id) {
            // Just add the task
            self.user_map
                .get_mut(&user_id)
                .unwrap()
                .push(Task {
                    task_id: self.id,
                    description: task_description,
                    tags: tags.into_iter().collect::<HashSet<String>>(),
                    due_date,
                });
            // Increase the global id
            self.id += 1;
            return self.id - 1;
        }
        // Otherwise, create a new user
        self.user_map.insert(
            user_id,
            vec![Task {
                task_id: self.id,
                description: task_description,
                tags: tags.into_iter().collect::<HashSet<String>>(),
                due_date,
            }]
        );
        self.id += 1;
        self.id - 1
    }

    fn get_all_tasks(&self, user_id: i32) -> Vec<String> {
        if
            !self.user_map.contains_key(&user_id) ||
            self.user_map.get(&user_id).unwrap().is_empty()
        {
            return vec![];
        }
        // Get the task vector
        let mut ret_vec = (*self.user_map.get(&user_id).unwrap()).clone();
        // Sort by due date
        ret_vec.sort_by(|lhs, rhs| { lhs.due_date.cmp(&rhs.due_date) });
        // Return the description vector
        ret_vec
            .into_iter()
            .map(|x| x.description)
            .collect()
    }

    fn get_tasks_for_tag(&self, user_id: i32, tag: String) -> Vec<String> {
        if
            !self.user_map.contains_key(&user_id) ||
            self.user_map.get(&user_id).unwrap().is_empty()
        {
            return vec![];
        }
        // Get the task vector
        let mut ret_vec = (*self.user_map.get(&user_id).unwrap()).clone();
        // Sort by due date
        ret_vec.sort_by(|lhs, rhs| { lhs.due_date.cmp(&rhs.due_date) });
        // Return the description vector
        ret_vec
            .into_iter()
            .filter(|x| x.tags.contains(&tag))
            .map(|x| x.description)
            .collect()
    }

    fn complete_task(&mut self, user_id: i32, task_id: i32) {
        if
            !self.user_map.contains_key(&user_id) ||
            self.user_map.get(&user_id).unwrap().is_empty()
        {
            return;
        }
        self.user_map
            .get_mut(&user_id)
            .unwrap()
            .retain(|x| (*x).task_id != task_id);
    }
}
