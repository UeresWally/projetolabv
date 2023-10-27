package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import br.gov.sp.fatec.projetolab5.entity.Documento;
import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.AnotacaoRepository;
import br.gov.sp.fatec.projetolab5.repository.DocumentoRepository;
import br.gov.sp.fatec.projetolab5.repository.UsuarioRepository;

@SpringBootTest
public class DocumentoServiceImplTest {
    
    @Autowired
    private DocumentoServiceImpl service;

    @MockBean
    private UsuarioRepository usuarioRepo;

    @MockBean
    private DocumentoRepository docRepo;

    @Test
    public void novoDocumentoTestUsuarioNaoEncontrado() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Wallace");
        usuario.setSenha("admin");


        Documento documento = new Documento();
        documento.setNumero(1L);
        documento.setDigito(123132);
        documento.setTipo("rg");
        documento.setDataEmissao(LocalDate.now());
        documento.setUsuario(usuario);
        Mockito.when(usuarioRepo.save(any())).thenReturn(usuario);

        assertThrows(ResponseStatusException.class, () -> {
            service.novoDocumento(documento);
        });

    }
    @Test
    public void novoDocumentoTestIdJaExiste() {


        Documento documento = new Documento();
        documento.setId(1L);

        assertThrows(ResponseStatusException.class, () -> {
            service.novoDocumento(documento);
        });

    }

    @Test
    public void novoDocumentoTestNumeroNull() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Wallace");
        usuario.setSenha("admin");


        Documento documento = new Documento();
        documento.setNumero(1L);
        documento.setDigito(123132);
        documento.setTipo("rg");
        documento.setDataEmissao(LocalDate.now());
        documento.setUsuario(usuario);
        Mockito.when(usuarioRepo.save(any())).thenReturn(usuario);

        assertThrows(ResponseStatusException.class, () -> {
            service.novoDocumento(documento);
        });
    }

    @Test
    public void novoDocumentoTestUsuarioIdNull() {
        Usuario usuario = new Usuario();
        usuario.setId(null);
        usuario.setNome("Wallace");
        usuario.setSenha("admin");


        Documento documento = new Documento();
        documento.setNumero(1L);
        documento.setDigito(123132);
        documento.setTipo("rg");
        documento.setDataEmissao(LocalDate.now());
        documento.setUsuario(usuario);
        Mockito.when(usuarioRepo.save(any())).thenReturn(usuario);

        assertThrows(ResponseStatusException.class, () -> {
            service.novoDocumento(documento);
        });
    }



    @Test
    public void novoDocumentoTestusuarioNull() {

        Documento documento = new Documento();
        documento.setNumero(null);
        documento.setDigito(123132);
        documento.setTipo("rg");
        documento.setDataEmissao(LocalDate.now());
        documento.setUsuario(null);

        assertThrows(ResponseStatusException.class, () -> {
            service.novoDocumento(documento);
        });
    }

    @Test
    public void novoDocumentoTestTipoNull() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Wallace");
        usuario.setSenha("admin");


        Documento documento = new Documento();
        documento.setNumero(1L);
        documento.setDigito(12312412);
        documento.setTipo(null);
        documento.setDataEmissao(LocalDate.now());
        documento.setUsuario(usuario);
        Mockito.when(usuarioRepo.save(any())).thenReturn(usuario);

        assertThrows(ResponseStatusException.class, () -> {
            service.novoDocumento(documento);
        });
    }

    @Test
    public void novoDocumentoTestTipoBlank() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Wallace");
        usuario.setSenha("admin");


        Documento documento = new Documento();
        documento.setNumero(1L);
        //documento.setDigito(null);
        documento.setTipo(" ");
        documento.setDataEmissao(LocalDate.now());
        documento.setUsuario(usuario);
        Mockito.when(usuarioRepo.save(any())).thenReturn(usuario);

        assertThrows(ResponseStatusException.class, () -> {
            service.novoDocumento(documento);
        });
    }


    @Test
    public void novoDocumentoTestDataEmissaoNull() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Wallace");
        usuario.setSenha("admin");


        Documento documento = new Documento();
        documento.setNumero(1L);
        documento.setDigito(123132);
        documento.setTipo("rg");
        documento.setDataEmissao(null);
        documento.setUsuario(usuario);
        Mockito.when(usuarioRepo.save(any())).thenReturn(usuario);

        assertThrows(ResponseStatusException.class, () -> {
            service.novoDocumento(documento);
        });
    }

    @Test
    public void novoDocumentoTestok() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Wallace");
        usuario.setSenha("admin");
        Optional<Usuario> usuarioOp = Optional.of(usuario);
        Mockito.when(usuarioRepo.findById(1L)).thenReturn(usuarioOp);


        Documento documento = new Documento();
        documento.setNumero(1L);
        documento.setDigito(123132);
        documento.setTipo("rg");
        documento.setDataEmissao(LocalDate.now());
        documento.setUsuario(usuario);
        Mockito.when(docRepo.save(any())).thenReturn(documento);

        assertEquals(1L,
                service.novoDocumento(documento).getNumero());

    }
}
