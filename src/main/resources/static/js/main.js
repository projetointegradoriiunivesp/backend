    AOS.init();

    document.addEventListener('DOMContentLoaded', function () {
      const deleteButtons = document.querySelectorAll('.delete-button');
      deleteButtons.forEach(button => {
        button.addEventListener('click', function () {
          const id = this.getAttribute('data-id');
          const password = prompt("Digite a senha para confirmar a exclusão:");
          if (password !== null) {
            fetch(`/agendamentos/${id}`, {
              method: 'DELETE',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify({ password: password }),
            })
            .then(response => {
              if (response.ok) {
                this.closest('tr').remove();
              } else if (response.status === 403) {
                alert("Senha incorreta. Não foi possível excluir.");
              } else {
                alert("Erro ao excluir.");
              }
            })
            .catch(() => alert("Erro ao excluir."));
          }
        });
      });
    });