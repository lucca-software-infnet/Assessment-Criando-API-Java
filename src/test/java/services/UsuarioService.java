package services;

import org.example.model.Usuario;
import org.example.dto.UsuarioDTOinput;
import org.example.dto.UsuarioDTOutput;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;




public class UsuarioService {
    private List<Usuario> listaUsuarios;
    private ModelMapper modelMapper;

    public UsuarioService() {
        this.listaUsuarios = new ArrayList<>();
        this.modelMapper = new ModelMapper();
    }

    public List<UsuarioDTOutput> listar() {
        List<UsuarioDTOutput> listaDTO = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            UsuarioDTOutput usuarioDTO = modelMapper.map(usuario, UsuarioDTOutput.class);
            listaDTO.add(usuarioDTO);
        }
        return listaDTO;
    }

    public void inserirUsuario(UsuarioDTOinput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        listaUsuarios.add(usuario);
    }

    public void alterarUsuario(UsuarioDTOinput usuarioDTOInput) {
        int id = usuarioDTOInput.getId();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == id) {
                Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
                listaUsuarios.set(i, usuario);
                return;
            }
        }
    }

    public UsuarioDTOutput buscarUsuarioPorId(Long id) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == id) {
                return modelMapper.map(usuario, UsuarioDTOutput.class);
            }
        }
        return null;
    }

    public void excluirUsuario(long id) {
        listaUsuarios.removeIf(usuario -> usuario.getId() == id);
    }
}
