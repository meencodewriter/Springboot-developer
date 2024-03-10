// Path: src/main/resources/static/js/article.js

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

const modifyButton = document.getElementById('modify-btn');

if(modifyButton) {
  modifyButton.addEventListener('click',() => {
    let param = {
      title: document.getElementById('title').value,
      content: document.getElementById('content').value
    }
    const id = document.getElementById('article-id').value;
    console.log('param', param);
    fetch(`/api/articles/${id}`,
      {
      method: "PUT",
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(param)
    }).then(res => {
      if(res.status === 200) {
        alert('Article modified successfully');
        location.replace('/articles');
      } else {
        alert('Failed to modify article');
      }
    });
  });
}

const createButton = document.getElementById('create-btn');

if(createButton) {
  createButton.addEventListener('click',() => {
    let param = {
      title: document.getElementById('title').value,
      content: document.getElementById('content').value
    }
    console.log('param', param);
    fetch(`/api/article`, {
      method: "POST",
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(param)
    }).then(res => {
      if(res.status === 201) {
        alert('Article created successfully');
        location.replace('/articles');
      } else {
        alert('Failed to create article');
      }
    });
  });
}