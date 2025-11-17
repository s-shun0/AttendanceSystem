let store = {};

module.exports = {
    save(email, token) {
        store[token] = email;
    },
    getEmail(token) {
        return store[token];
    }
};
