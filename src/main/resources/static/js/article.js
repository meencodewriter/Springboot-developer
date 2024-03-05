const deleteButton = document.getElementById('delete-btn');

if(deleteButton) {
  deleteButton.addEventListener('click',() => {
    const id = document.getElementById('article-id').value;
    fetch(`/api/articles/${id}`, {
      method: "DELETE"
    }).then(res => {
      if(res.status === 200) {
        alert('Article deleted successfully');
        location.replace('/articles');
      } else {
        alert('Failed to delete article');
      }
    })
  })
}