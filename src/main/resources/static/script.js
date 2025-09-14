function getToken() {
    return localStorage.getItem("token");
  }
  
  function authHeaders() {
    return { "Authorization": "Bearer " + getToken(), "Content-Type": "application/json" };
  }
  
  function logout() {
    localStorage.clear();
    window.location.href = "login.html";
  }
  