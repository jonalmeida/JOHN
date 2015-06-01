package com.jonalmeida.john.item;

public enum ItemType {
    Story {
        public String toString() {
            return "story";
        }
    },
    Comment {
        public String toString() {
            return "comment";
        }
    },
    Ask {
        public String toString() {
            return "ask";
        }
    },
    Job {
        public String toString() {
            return "job";
        }
    },
    Poll {
        public String toString() {
            return "poll";
        }
    },
    PollOpt {
        public String toString() {
            return "pollopt";
        }
    },
    Unknown {
        public String toString() {
            return "unknown";
        }
    }
}
