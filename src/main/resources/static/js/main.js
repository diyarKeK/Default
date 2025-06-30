document.addEventListener('DOMContentLoaded', async () => {
  try {
    const response = await fetch('/api/verify', {
      method: 'GET',
      credentials: 'include'
    })

    if (!response.ok) {
      window.location.href = '/login'
      return
    }

    const user = await response.json()

    document.getElementById('name').textContent = user.name
    document.getElementById('email').textContent = user.email
    document.getElementById('bio').textContent = user.bio

    document.getElementById('logout').addEventListener('click', async () => {
      try {
        const logout_response = await fetch('/api/logout', {
            method: 'POST',
            credentials: 'include'
        })

        alert(logout_response.message)

        window.location.href = '/login'
      } catch (err_1) {
        alert(err_1.message)
      }
    })

  } catch (err) {

  }
})
