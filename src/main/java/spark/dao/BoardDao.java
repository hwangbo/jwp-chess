package spark.dao;

import chess.board.ChessBoard;
import chess.location.Location;
import chess.piece.type.Piece;
import spark.vo.PieceVo;

import java.sql.SQLException;
import java.util.Map;

public class BoardDao {
    public void resetBoard(ChessBoard chessBoard, int gameId) throws SQLException {
        Map<Location, Piece> board = chessBoard.getBoard();
        PieceDao pieceDao = new PieceDao();

        pieceDao.deleteGame(gameId);
        addPieces(gameId, board, pieceDao);
    }

    private void addPieces(int gameId, Map<Location, Piece> board, PieceDao pieceDAO) throws SQLException {
        for (Map.Entry<Location, Piece> entry : board.entrySet()) {
            Location location = entry.getKey();
            Piece piece = entry.getValue();

            PieceVo pieceVo = new PieceVo(gameId
                    , Character.toString(piece.getName())
                    , location.getRowValue()
                    , Character.toString(location.getColValue()));

            pieceDAO.addPiece(pieceVo);
        }
    }
}
