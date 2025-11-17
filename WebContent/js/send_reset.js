document.getElementById("resetForm").addEventListener("submit", function(e) {
    e.preventDefault();

    var email = document.getElementById("email").value;

    fetch("http://localhost:3001/send-reset", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email: email })
    })
    .then(function(res) {
        return res.json();
    })
    .then(function(data) {
        document.getElementById("resultMessage").textContent = data.message;
    })
    .catch(function(error) {
        console.error("Error:", error);
        document.getElementById("resultMessage").textContent =
            "送信エラーが発生しました。";
    });
});
